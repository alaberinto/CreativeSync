package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Position;
import services.AccountService;
import services.PositionService;
import viewModels.UsersView;

/**
 *
 * @author Mason
 */
public class UsersServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
                HttpSession session = request.getSession();
                AccountService as = new AccountService();
                PositionService ps = new PositionService();
                
                Account user = (Account) session.getAttribute("user");
                String searchBar = request.getParameter("search");
                
                ArrayList<UsersView> users = as.getUsersView(user, searchBar);
                ArrayList<Position> positions = ps.getAllPositions();
                
                request.setAttribute("positions", positions);
                request.setAttribute("users", users);
                
                getServletContext().getRequestDispatcher("/WEB-INF/Users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if(action.equals("addUser")) {
            response.sendRedirect("AddUser");
        }
        
    }
}