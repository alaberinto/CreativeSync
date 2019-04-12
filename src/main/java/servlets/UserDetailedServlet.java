package servlets;

import dataaccess.DBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Genre;
import models.Language;
import models.Location;
import models.Position;

import services.AccountService;
import services.GenreService;
import services.LanguageService;
import services.LocationService;
import services.PositionService;
import viewModels.UsersView;

/**
 * A controller servlet to manage actions related to the User object. 
 * It does the request processing, calls the related Service methods and forwards to the view (JSP) 
 * 
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 */
public class UserDetailedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        AccountService as = new AccountService();

        String name = request.getParameter("name");
        UsersView user = as.getUsersViewMyAccount(name);

        if (user == null) {
            request.setAttribute("badFeedback", "User Not Found!");
            response.sendRedirect("Users");

        } else {
            request.setAttribute("myUser", user);
            getServletContext().getRequestDispatcher("/WEB-INF/UserDetailed.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        Account account = (Account) request.getAttribute("myUser");
        String action = request.getParameter("action");
        Account deletingUser = as.getUserByName(request.getParameter("thisUser"));
        Account loggedInUser = (Account) session.getAttribute("user");

        if (action.equals("edit")) {
            request.setAttribute("myUser", account);
            getServletContext().getRequestDispatcher("/WEB-INF/EditUser.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            String feedback = as.delete(loggedInUser, deletingUser);

            if (feedback != null) {
                response.sendRedirect("UserDetailed?name=" + deletingUser.getFirstname() + " " + deletingUser.getLastname());
            } else {
                request.setAttribute("goodFeedback", "User Deleted Successfully!");
                response.sendRedirect("Users");
            }
        } else {
            response.sendRedirect("UserDetailed?name=" + request.getParameter("thisUser"));
        }
    }
}
