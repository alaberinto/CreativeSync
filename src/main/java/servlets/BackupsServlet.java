package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Backup;
import services.AccountService;
import services.BackupService;
import services.TitleService;

/**
 * BackupsServlet is a class managing the content displayed on the page Backups.JSP.
 *
 *  @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 */
public class BackupsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BackupService bs = new BackupService();
        if (request.getParameter("backupDatabase") != null) {
            bs.runBackup();
        }
        ArrayList<Backup> backups = bs.getAllBackups();
        request.setAttribute("backups", backups);
        getServletContext().getRequestDispatcher("/WEB-INF/Backups.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BackupService bs = new BackupService();
            String backupId = request.getParameter("backupId");
        try {
            bs.restoreDatabase(backupId);
            ArrayList<Backup> backups = bs.getAllBackups();
            request.setAttribute("backups", backups);
        } catch (Exception ex) {
            request.setAttribute("badFeedback", "Error restoring database");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/Backups.jsp").forward(request, response);
    }
}
