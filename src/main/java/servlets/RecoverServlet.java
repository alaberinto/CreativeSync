package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

/**
 * A controller servlet to manage actions related to the Recover object details. 
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
public class RecoverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/Recover.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountService as = new AccountService();

        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        //Security 
        if(action == null) {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/Recover.jsp").forward(request, response);

        }else if(action.equals("login")) {
            getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        } 
        else if (action.equals("recover")) {
            String email = request.getParameter("email");
            session.setAttribute("recoverEmail", email);
            
            if (email != null) {
                as.recover(email, getServletContext().getRealPath("/WEB-INF"));
            }
            
            request.setAttribute("sent", "");
            getServletContext().getRequestDispatcher("/WEB-INF/Recover.jsp").forward(request, response);

        } else if (action.equals("codeRecover")) {
            String rid = request.getParameter("rid");
            String email = (String) session.getAttribute("recoverEmail");

            if (as.checkRecovery(rid, email)) {
                getServletContext().getRequestDispatcher("/WEB-INF/RecoverPassword.jsp").forward(request, response);
            } else {
                request.setAttribute("badFeedback", "Invalid Recovery Code!");
                request.setAttribute("sent", "");
                getServletContext().getRequestDispatcher("/WEB-INF/Recover.jsp").forward(request, response);
            }

        } else if (action.equals("retry")) {
            String x = "";
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/Recover.jsp").forward(request, response);
        }
    }
}
