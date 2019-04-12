package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import services.AccountService;

/**
 * LoginServlet is a class managing the content displayed on the page Login.JSP.
 *
 *  @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            request.setAttribute("goodFeedback", "Successfully Logged Out!");
        }
        
        session.invalidate();
        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        AccountService as = new AccountService();

        String action = request.getParameter("action");

        //When login button is selected.
        if (action.equals("login")) {
            String email = request.getParameter("username");
            String password = request.getParameter("password");
            Account user = as.validate(email, password);

            //If valid then forward to Titles. Otherwise create feedback message.
            if (user != null) {
                session.setAttribute("user", user);
                response.sendRedirect("Titles");
            } else {
                request.setAttribute("badFeedback", "Login Credentials Invalid!");
                getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
            }

        } //When "forgot Password" is selected.
        else if (action.equals("recover")) {
            getServletContext().getRequestDispatcher("/WEB-INF/Recover.jsp").forward(request, response);
        }
    }
}
