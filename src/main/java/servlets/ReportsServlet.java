package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import services.AccountService;
import services.ReportService;
import services.TitleService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
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
        
        ArrayList<UsersView> usersView;
        ArrayList<TitlesView> titlesView;
        
        try {
            if (reportType.equals("activeUsers")) {
                ArrayList<Account> users = ac.getAllActiveUsers();
                usersView = new ArrayList<UsersView>();
                
                for(int i = 0; i < users.size(); i++) {
                    usersView.add(new UsersView(users.get(i)));
                }
                request.setAttribute("list", usersView);
                
            } else if(reportType.equals("positions")) {
                String[] positionId = request.getParameterValues("userType");
                rs.viewUserByPosition(positionId);
            } else if (reportType.equals("specificUsers")) {
                String[] specificUsers = request.getParameterValues("users");
                rs.viewUserInfo(specificUsers);
            } else if (reportType.equals("activeTitles")) {
                rs.getAllActiveTitles();
            } else if (reportType.equals("compTitles")) {
                rs.getAllCompletedTitles();
            } else if (reportType.equals("specificTitles")) {
                String[] tId = request.getParameterValues("titles");
                //   rs.viewTitleInformation(tId);
            }
        } catch (Exception ex) {
            
        }
        
        request.setAttribute("reportType", reportType);
        getServletContext().getRequestDispatcher("/WEB-INF/Reports.jsp").forward(request, response);
    }
}
