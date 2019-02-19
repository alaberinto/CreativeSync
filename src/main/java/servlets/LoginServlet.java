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
 *
 * @author Mason
 */
public class LoginServlet extends HttpServlet {

    /**
     * TODO everything here
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
    }

    /**
     * Gets action from page. If login button is selected then validate. If valid then forward to Titles.JSP. Else reload login. 
     * If recover is selected then forward to Recover.JSP
     * 
     * TODO: Lots xD
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        AccountService as = new AccountService();

        String action = request.getParameter("action");
        String feedback = null;
        
        //When login button is selected.
        if(action.equals("login")) {
            String email = request.getParameter("username");
            String password = request.getParameter("password");
            Account user = as.validate(email, password);
            
            //If valid then forward to Titles. Otherwise create feedback message.
            if(user != null) {
                
                //Add user info to session.
                session.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/WEB-INF/Titles.jsp").forward(request, response);
            }
            else
                //Set feedback field.
                feedback = "Invalid login";
        }
        //When "forgot Password" is selected.
        else if(action.equals("recover")) {
            getServletContext().getRequestDispatcher("/WEB-INF/Recover.jsp").forward(request, response);
        }
        
        //Print out feedback and return to login.
        request.setAttribute("feedback", feedback); //Implement feedback.
        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
    }
}
