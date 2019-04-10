package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Account;
import services.AccountService;
import services.ReportService;
import services.TitleService;

/**
 *
 * @author Omurbek
 */
public class ReportsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        javax.servlet.http.HttpSession session = request.getSession();

        ReportService rs = new ReportService();
        AccountService as = new AccountService();
        TitleService ts = new TitleService();
        String reportType = request.getParameter("name");
        if (reportType.equals("specificUsers")) {
            request.setAttribute("allUsers", as.getAllUsers());
        }
        if (reportType.equals("specificTitles")) {
            request.setAttribute("allTitles", ts.getAllTitles());
        }

        getServletContext().getRequestDispatcher("/WEB-INF/Reports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReportService rs = new ReportService();
        AccountService ac = new AccountService();
        String reportType = request.getParameter("reportInput");
        try {
            if (reportType == "activeUsers") {
                ac.getAllActiveUsers();
            } else if (reportType == "positions") {
                String[] positionId = request.getParameterValues("userType");
                rs.viewUserByPosition(positionId);
            } else if (reportType == "specificUsers") {
                String[] specificUsers = request.getParameterValues("users");
                rs.viewUserInfo(specificUsers);
            } else if (reportType == "activeTitles") {
                rs.getAllActiveTitles();
            } else if (reportType == "compTitles") {
                rs.getAllCompletedTitles();
            } else if (reportType == "specificTitles") {
                String[] tId = request.getParameterValues("titles");
             //   rs.viewTitleInformation(tId);
            }
        } catch (Exception ex) {

        }

        String reportInput = request.getParameter("reportInput");
        String[] userIds = request.getParameterValues("userIds");
        rs.viewUserInfo(userIds);
    }
}
