package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;
import services.EmailService;

/**
 *
 * @author Mason
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

        } else if (action.equals("recover")) {
            String email = request.getParameter("email");

            if (email != null) {
                session.setAttribute("recoverEmail", email);
                as.recover(email, getServletContext().getRealPath("/WEB-INF"));
                session.setAttribute("sent", "");
                getServletContext().getRequestDispatcher("/WEB-INF/Recover.jsp").forward(request, response);
            }

        } else if (action.equals("codeRecover")) {
            String rid = request.getParameter("rid");
            String email = (String) session.getAttribute("recoverEmail");

            if (as.checkRecovery(rid, email)) {
                getServletContext().getRequestDispatcher("/WEB-INF/RecoverPassword.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/Recover.jsp").forward(request, response);
            }

        } else if (action.equals("retry")) {
            String x = "";
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/Recover.jsp").forward(request, response);
        }
    }
}
