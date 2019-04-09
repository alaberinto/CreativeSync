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

/**
 *
 * @author Omurbek
 */
public class ReportsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            String name = request.getParameter("name");
            request.setAttribute("name", name);
        
        String reportTitle = "";

        javax.servlet.http.HttpSession session = request.getSession();
        AccountService as = new AccountService();
        ArrayList<Account> filteredUsers = new ArrayList<Account>();
        try {
            ArrayList<Account> users = as.getAllUsers();
            
         

            String reporttype = request.getParameter("reporttype");
            //users sub menu
            String allusers = request.getParameter("allusers");
            String designers = request.getParameter("designers");
            String coordinators = request.getParameter("coordinators");
            String admins = request.getParameter("admins");
            String freelancers = request.getParameter("freelancers");

            //titles sub menu
            String freelancerstitles = request.getParameter("freelancerstitles");
            String alltitles = request.getParameter("alltitles");

            if (reporttype == null) {

                session.setAttribute("filteredusers", null);
                session.setAttribute("titleReportList", null);
            } else if (reporttype.equals("userreport")) {
                if (allusers != null) {
                    reportTitle = "All Users";
                } else {
                    if (designers != null) {
                        reportTitle += "Designer Leads ";
                    }

                    if (coordinators != null) {
                        reportTitle += "Coordinators ";
                    }
                    if (admins != null) {
                        reportTitle += "Admins ";
                    }
                    if (freelancers != null) {
                        reportTitle += "Freelancers ";
                    }
                }

                for (Account a : users) {

                    if (freelancers != null && a.getPosition().getPositionId() == 4) {
                        filteredUsers.add(a);
                    } else if (designers != null && a.getPosition().getPositionId() == 3) {
                        filteredUsers.add(a);
                    } else if (coordinators != null && a.getPosition().getPositionId() == 2) {
                        filteredUsers.add(a);
                    } else if (admins != null && a.getPosition().getPositionId() == 1) {
                        filteredUsers.add(a);
                    } else if (allusers != null) {
                        filteredUsers.add(a);
                    }

                }
                session.setAttribute("filteredusers", filteredUsers);
                session.setAttribute("titleReportList", null);
            } else if (reporttype.equals("titlereport")) {

                List<Account> titleUsers = new ArrayList<Account>();

                if (alltitles != null) {
                    titleUsers = users;
                     reportTitle += "Titles ";
                } else if (freelancerstitles != null) {
                    reportTitle += "Freelancer title ";
                    for (Account a : users) {
                        if (a.getPosition().getPositionId() == 4) {
                            titleUsers.add(a);
                        }
                    }
                }

                session.setAttribute("filteredusers", null);
                session.setAttribute("titleReportList", titleUsers);
            }
             session.setAttribute("title", reportTitle);


        } catch (Exception e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/WEB-INF/Reports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
