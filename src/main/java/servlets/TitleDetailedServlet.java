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
        
        TitleService ts = new TitleService();
        AccountService as = new AccountService();
        String titleName = request.getParameter("name");

        TitlesView title = ts.getTitlesViewByName(titleName);
        ArrayList<TitleHasAccount> tha = new ArrayList(as.getFreelancersByTitle(title.getTitle()));
        
        if(title == null) {
            request.setAttribute("badFeedback", "Title Not Found!");
        }
        else {
            request.setAttribute("frees", tha);
            request.setAttribute("view", title);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/TitleDetailed.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        FileService fs = new FileService();
        HttpSession session = request.getSession();
        Title title = (Title) session.getAttribute("title");

        if(action == null) {
            //Get File From JSP
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
              //s      fs.handleUpload(multiparts, title.getName());

                } catch (FileUploadException ex) {
                    Logger.getLogger(TitleDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(action.equals("viewAsset")) {
            

        }else if(action.equals("deleteAsset")) {
            try {
                fs.deleteAsset(title.getName(), request.getParameter("assetName"));
            } catch (DbxException ex) {
                Logger.getLogger(TitleDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        doGet(request, response);
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
}
