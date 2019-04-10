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

        try {
            if (request.getParameter("activeUsers") != null) {
                rs.getAllActiveUsers();
            }
            if (request.getParameter("userPosition") != null) {
                String[] positionId = request.getParameterValues("userType");
                rs.viewUserByPosition(positionId);
            }
            if(request.getParameter("specificUsers")!=null){
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String email = request.getParameter("email");
                rs.viewUserInfo(firstname, lastname, email);
            }
            if(request.getParameter("activeTitles")!= null){
                rs.getAllActiveTitles();
            }
            if(request.getParameter("compTitles")!=null){
                rs.getAllCompletedTitles();
            }
            if(request.getParameter("specificTitles")!=null){
                String tId = request.getParameter("titleId");
                rs.viewTitleInformation(tId);
            }
        } catch (Exception ex) {

        }

        getServletContext().getRequestDispatcher("/WEB-INF/Reports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
