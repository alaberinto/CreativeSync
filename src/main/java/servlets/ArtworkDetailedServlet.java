package servlets;

import com.dropbox.core.DbxException;
import dataaccess.DBException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Artwork;
import models.Title;
import models.TitleHasAccount;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.ArtworkService;
import services.EmailService;
import services.FileService;

/**
 * ArtworkDetailedServlet is a class managing the content displayed on the page
 * ArtworkDetailed.JSP.
 *
 * @author Mason Hill
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
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("roundsFilled")) {
                    session.setAttribute("roundsFilled", Integer.parseInt(cookies[i].getValue()));
                } else if (cookies[i].getName().equals("c1")) {
                    session.setAttribute("showUpload", cookies[i].getValue());
                } else if (cookies[i].getName().equals("approvedCookie")) {
                    session.setAttribute("approvedCookie", cookies[i].getValue());
                }
            }
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
//            //            //get rounds
            ArtworkService as = new ArtworkService();
            List<Artwork> rounds;
            rounds = as.getAllRounds(titleId);
//            
//            //gets artwork for each round
//            List<Artwork> roundArt;
//            roundArt = as.getAllArtworkByTitleId(titleId);
//            request.setAttribute("roundArt", roundArt);
//            
//            //check if there are any rounds & artwork
            if (rounds.isEmpty() == false) {
                request.setAttribute("roundsFilled", 1);
                request.setAttribute("rounds", rounds);
            } else if (session.getAttribute("roundsFilled") != null) {
                request.setAttribute("roundsFilled", 1);
            } else {
                request.setAttribute("roundsFilled", 0);
            }
//            
//            //check for approve/deny for all rounds
//            if (as.getArtworkStatus(titleId, 1) == 1) {
//                request.setAttribute("status", "1");
//            } else if (as.getArtworkStatus(titleId, 2) == 2) {
//                request.setAttribute("status", "2");
//            }
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

            //title id & name retrieval & max round
            int titleId = (int) session.getAttribute("titleId");
//            String titleName = (String) session.getAttribute("titleName");

//            int maxRound = as.findMaxRound(titleId);
//
//            //get rounds         
            List<Artwork> rounds;
            rounds = as.getAllRounds(titleId);
            request.setAttribute("rounds", rounds);
//
//            //gets artwork for each round
//            List<Artwork> roundArt;
//            roundArt = as.getAllArtworkByTitleId(titleId);
//            request.setAttribute("roundArt", roundArt);
            //get title object to use for inserting artwork
//            Title title = as.getArtTitle(titleId);
            //check if there are any rounds & artwork
            if (rounds.isEmpty() == false) {
                request.setAttribute("roundsFilled", 1);
                request.setAttribute("rounds", rounds);
            } else {
                request.setAttribute("roundsFilled", 0);
            }
            /**
             * *************Add/Upload artwork***********
             */
            String action = request.getParameter("action");

            String uploaded;

            if (action.equalsIgnoreCase("uploadArtwork")) {
                request.setAttribute("showUpload", "show");
                request.setAttribute("roundsFilled", 1);
                Cookie c1 = new Cookie("c1", "showUpload");
                Cookie roundsFilled = new Cookie("roundsFilled", "1");
                c1.setMaxAge(60 * 30);
                c1.setPath("/");
                response.addCookie(c1);
                roundsFilled.setMaxAge(60 * 30);
                roundsFilled.setPath("/");
                response.addCookie(roundsFilled);
            }

            /**
             * ******Approve or Deny************
             */
            //changes the approve/deny message when the form is posted
            String approved = request.getParameter("approve");
            if (approved != null) {
                request.setAttribute("approved", "approved");
                Cookie approvedCookie = new Cookie("approved", "approved");
                approvedCookie.setMaxAge(10 * 60);
                approvedCookie.setPath("/");
                response.addCookie(approvedCookie);
                Cookie[] cookies = request.getCookies();

                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("showUpload")) {
                        cookies[i].setMaxAge(-1);
                        session.removeAttribute("showUpload");
                    }
                }
            }
//            System.out.print(roundArt.size());

            if (approved != null) {
//                for (int i = 0; i < roundArt.size(); i++) {
//                    as.updateArtworkStatus(roundArt.get(i).getArtworkId(), 1);
//                }
                request.setAttribute("status", 1); //1 if round is approved
            }

            Account user = (Account) session.getAttribute("user");
            int position = user.getPosition().getPositionId();

            if (position == 4) { //freelancer       
                request.setAttribute("position", "0"); //freelancer
            } else {
                request.setAttribute("position", "1"); //design lead, and others
            }

            getServletContext().getRequestDispatcher("/WEB-INF/ArtworkDetailed.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ArtworkDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
