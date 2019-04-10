package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import services.AccountService;
import services.ReportService;
import services.TitleService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import viewModels.TitlesView;
import viewModels.UsersView;

/**
 *
 * @author Omurbek
 */
public class ReportsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        ReportService rs = new ReportService();
        AccountService as = new AccountService();
        TitleService ts = new TitleService();
        String reportType = request.getParameter("name");
        
        

        if (reportType != null) {
            if (reportType.equals("specificUsers")) {
                request.setAttribute("allUsers", as.getAllUsers());
            }
            if (reportType.equals("specificTitles")) {
                request.setAttribute("allTitles", ts.getAllTitles());
            }

            request.setAttribute("name", reportType);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/Reports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReportService rs = new ReportService();
        AccountService ac = new AccountService();
        String reportType = request.getParameter("reportInput");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        try {
            if (reportType.equals("activeUsers")) {
                ArrayList<UsersView> users = ac.getAllActiveUsers();
                request.setAttribute("list", users);
                request.setAttribute("reportgendate", date);

            } else if (reportType.equals("positions")) {
                String[] positionId = request.getParameterValues("userType");
                ArrayList<UsersView> users = rs.viewUserByPosition(positionId);
                request.setAttribute("list", users);
                request.setAttribute("reportgendate", date);

            } else if (reportType.equals("specificUsers")) {
                String[] specificUsers = request.getParameterValues("users");
                ArrayList<UsersView> users = rs.viewUserInfo(specificUsers);
                request.setAttribute("list", users);
                request.setAttribute("reportgendate", date);

            } else if (reportType.equals("activeTitles")) {
                ArrayList<TitlesView> title = rs.getAllActiveTitles();
                request.setAttribute("list", title);
                request.setAttribute("reportgendate", date);

            } else if (reportType.equals("compTitles")) {
                ArrayList<TitlesView> title = rs.getAllCompletedTitles();
                request.setAttribute("list", title);
                request.setAttribute("reportgendate", date);

            } else if (reportType.equals("specificTitles")) {
                String[] tId = request.getParameterValues("titles");
                ArrayList<TitlesView> title = rs.viewTitleInformation(tId);
                request.setAttribute("list", title);
                request.setAttribute("reportgendate", date);
            }
        } catch (Exception ex) {

        }

        request.setAttribute("reportType", reportType);
        getServletContext().getRequestDispatcher("/WEB-INF/Reports.jsp").forward(request, response);
    }
}
