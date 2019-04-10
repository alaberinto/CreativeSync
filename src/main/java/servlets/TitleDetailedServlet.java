package servlets;

import com.dropbox.core.DbxException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Title;
import models.TitleHasAccount;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.AccountService;
import services.FileService;
import services.TitleService;
import viewModels.TitlesView;

/**
 *
 * @author Mason
 */
public class TitleDetailedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        TitleService ts = new TitleService();
        AccountService as = new AccountService();
        FileService fs = new FileService();
        String titleName = request.getParameter("name");

        TitlesView title = ts.getTitlesViewByName(titleName);
        ArrayList<TitleHasAccount> tha = new ArrayList(as.getFreelancersByTitle(title.getTitle()));
        session.setAttribute("title", title.getTitle());

        if (title == null) {
            request.setAttribute("badFeedback", "Title Not Found!");
        } else {
            request.setAttribute("frees", tha);
            request.setAttribute("view", title);

            double timeLeft = title.getTitle().getEndDate().getTime() - new Date().getTime();
            timeLeft = timeLeft / (1000 * 60 * 60 * 24);
            timeLeft = Math.rint(timeLeft);
            request.setAttribute("timeLeft", timeLeft);
            
            ArrayList<String> assets = null;
            try {
                assets = fs.getAssets(titleName);
            } catch (DbxException ex) {
                Logger.getLogger(TitleDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("assets", assets);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/TitleDetailed.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = null;
        FileService fs = new FileService();
        HttpSession session = request.getSession();
        Title title = (Title) session.getAttribute("title");
        List<FileItem> multiparts = null;
       

        //Check if its a file upload.
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                // This method grabs the action. Without this part, action will be null.
                // This is needed in order to differentiate whether its an artwork or an asset being uploaded.
                action = checkValue(multiparts);
            } catch (FileUploadException ex) {
                Logger.getLogger(TitleDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            action = request.getParameter("action");
        }

        String uploaded = null; //if not null, show failure message, hasn't been implemented for notifications yet
        switch (action) {
            case "uploadAsset":
                uploaded = fs.handleUpload(multiparts, title.getName(), "asset");
                session.setAttribute("uploaded", uploaded);
                break;
            case "uploadArtwork":
                //Get File From JSP
                uploaded = fs.handleUpload(multiparts, title.getName(), "artwork");
                session.setAttribute("uploaded", uploaded);
                break;
            case "viewAsset":
                break;
            case "deleteAsset":
                try {
                    fs.deleteAsset(title.getName(), request.getParameter("assetName"));
                } catch (DbxException ex) {
                    Logger.getLogger(TitleDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "deleteArtwork":
                try {
                    fs.deleteAsset(title.getName(), request.getParameter("artworkName"));
                } catch (DbxException ex) {
                    Logger.getLogger(TitleDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                break;
        }

        response.sendRedirect("TitleDetailed?name=" + title.getName());
        //request.setAttribute("name", title.getName());
        //doGet(request, response);
    }

    public static int getPercentageLeft(Date start, Date end) {
        long now = System.currentTimeMillis();
        long s = start.getTime();
        long e = end.getTime();
        if (s >= e || now >= e) {
            return 0;
        }
        if (now <= s) {
            return 100;
        }
        return (100) - ((int) ((e - now) * 100 / (e - s)));
    }

    private String checkValue(List<FileItem> multiparts) {
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
