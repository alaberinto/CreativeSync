package servlets;

import dataaccess.DBException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;

import services.AccountService;

/**
 *
 * @author Mason
 */
public class UserDetailedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Account user = (Account) session.getAttribute("user");

        request.setAttribute("fname", user.getFirstname());
        request.setAttribute("lname", user.getLastname());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("location", user.getLocation());

        // Position is admin
        getServletContext().getRequestDispatcher("/WEB-INF/UserDetailed.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        Account user = (Account) session.getAttribute("user");
        String upassword = user.getPassword();
        String fname = request.getParameter("fname");
        request.setAttribute("fname", fname);
        String lname = request.getParameter("lname");
        request.setAttribute("lname", lname);
        String password = request.getParameter("password");
        
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        String location = request.getParameter("location");
        request.setAttribute("location", location);
        try {
            if (action.equals("edit")) {
                if (password.equals(" ") || password.equals(null) || password.equals("") || password == null) {

                    as.update(user, fname, lname, email, upassword, location);
                } else {
                    as.update(user, fname, lname, email, password, location);
                }
            }
            if (action.equals("delete")) {

            }
        } catch (DBException ex) {
            Logger.getLogger(UserDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/UserDetailed.jsp").forward(request, response);

    }
}
