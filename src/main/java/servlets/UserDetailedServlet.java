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
        AccountService as = new AccountService();

        String name = request.getParameter("name");

        if (name == null) {
            request.setAttribute("badFeedback", "User Not Found!");
        } else {
            request.setAttribute("detailUser", as.getTitlesViewByName(name));
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/UserDetailed.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
