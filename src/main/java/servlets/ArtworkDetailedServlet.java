package servlets;

import com.dropbox.core.DbxException;
import dataaccess.DBException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Artwork;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.ArtworkService;
import services.EmailService;
import services.FileService;

/**
 * ArtworkDetailedServlet is a class managing the content displayed on the page ArtworkDetailed.JSP.
 *
 *  @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 */
public class ArtworkDetailedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();

            /*
            1 = admin
            2 = coordinator
            3 = design lead
            4 = freelancer
             */
            Account user = (Account) session.getAttribute("user");
            int position = user.getPosition().getPositionId();

            if (position == 4) { //freelancer       
                request.setAttribute("position", "0"); //freelancer
            } else {
                request.setAttribute("position", "1"); //design lead, and others
            }

            request.setAttribute("status", 0); //0 to see the buttons    

            //title id retrieval            
            int titleId = (int) session.getAttribute("titleId");

            //get rounds
            ArtworkService as = new ArtworkService();
            List<Artwork> rounds;
            rounds = as.getAllRounds(titleId);

            //gets artwork for each round
            List<Artwork> roundArt;
            roundArt = as.getAllArtworkByTitleId(titleId);
            request.setAttribute("roundArt", roundArt);

            //check if there are any rounds & artwork
            if (rounds.isEmpty() == false) {
                request.setAttribute("roundsFilled", 1);
                request.setAttribute("rounds", rounds);
            } else {
                request.setAttribute("roundsFilled", 0);
            }
            
            //check for approve/deny for all rounds
            if (as.getArtworkStatus(titleId, 1) == 1) {
                request.setAttribute("status","1");
            } else if (as.getArtworkStatus(titleId, 2) == 2) {
                request.setAttribute("status","2");
            }

            getServletContext().getRequestDispatcher("/WEB-INF/ArtworkDetailed.jsp").forward(request, response);
        } catch (DBException ex) {
            Logger.getLogger(ArtworkDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            ArtworkService as = new ArtworkService();
            EmailService es = new EmailService();

            //get information from round approval/denial
            String comment = request.getParameter("comment");

            //since there is only one status, either approve or deny, one is chosen
            String statusA = request.getParameter("approve");
            String statusD = request.getParameter("deny");
            String status = statusA + statusD;
//            int statusI = Integer.parseInt(status); //1 for approved, 2 for denied

            //for testing only - works for one round multiple have issues
            request.setAttribute("comment", comment); //gets the commnet
            request.setAttribute("status", status); //gets the number    

            //title id & name retrieval
            int titleId = (int) session.getAttribute("titleId");
            String titleName = (String) session.getAttribute("titleName");

            //get rounds         
            List<Artwork> rounds;
            rounds = as.getAllRounds(titleId);
            request.setAttribute("rounds", rounds);

            //gets artwork for each round
            List<Artwork> roundArt;
            roundArt = as.getAllArtworkByTitleId(titleId);
            request.setAttribute("roundArt", roundArt);

            //check if there are any rounds & artwork
            if (rounds.isEmpty() == false) {
                request.setAttribute("roundsFilled", 1);
                request.setAttribute("rounds", rounds);
            } else {
                request.setAttribute("roundsFilled", 0);
            }

            /**
             * *************Upload artwork***********
             */
            String action = null;
            List<FileItem> multiparts = null;
            FileService fs = new FileService();

            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    // This method grabs the action. Without this part, action will be null.
                    // This is needed in order to differentiate whether its an artwork or an asset being uploaded.
                    action = checkValue(multiparts);
                } catch (FileUploadException ex) {
                    Logger.getLogger(TitleDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String uploaded = null; //if not null, show failure message, hasn't been implemented for notifications yet
            String artUpload = request.getParameter("artUpload");

            if (artUpload != null) {
                //Get File From JSP
                uploaded = fs.handleUpload(multiparts, titleName, "artwork");

                if (uploaded != null) {
                    session.setAttribute("uploaded", uploaded);
                } else {
                    session.setAttribute("failed", "Could not upload artwork.");
                }
            }

            /**
             * ******Approve or Deny************
             */
            //changes the approve/deny message when the form is posted
            String approved = request.getParameter("approve");
            String denied = request.getParameter("deny");

            if (approved != null) {
                for (int i = 0; i < roundArt.size(); i++) {
                    as.updateArtworkStatus(i, 1);
//            es.sendMail(email, subject, template, tags);
                }
                request.setAttribute("status", 1); //1 if round is approved
            }

            if (denied != null) {
                for (int i = 0; i < roundArt.size(); i++) {
                    as.updateArtworkStatus(i, 2);
//            es.sendMail(email, subject, template, tags);
                }
                request.setAttribute("status", 2); //2 if round is denied
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/ArtworkDetailed.jsp").forward(request, response);
        } catch (DBException ex) {
            Logger.getLogger(ArtworkDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String checkValue(List<FileItem> multiparts) {
        String inputName = null;
        for (FileItem item : multiparts) {
            if (item.isFormField()) {
                inputName = (String) item.getFieldName();
                if (inputName.equalsIgnoreCase("action")) {
                    return item.getString();
                }
            }
        }
        return null;
    }
}
