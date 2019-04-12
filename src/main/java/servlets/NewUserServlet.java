package servlets;

import dataaccess.DBException;
import services.AccountService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A controller servlet to manage actions related to the New user object details. 
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
public class NewUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/NewUser.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String location = request.getParameter("location");
        String rate = request.getParameter("rate");
        Double userRate = Double.parseDouble(rate);
        String position = request.getParameter("position");
        int positionId = Integer.parseInt(position);

//        try {
//            //( userId,  password,  firstname, lastname,  email,  location,  rate,  portfolio,  isactive,  imagePath); 
//
////            as.insert(null, positionId, password, fname, lname, email, location, userRate, null, new Short("1"), null);
////        } catch (DBException ex) {
//            Logger.getLogger(NewUserServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        getServletContext().getRequestDispatcher("/WEB-INF/NewUser.jsp").forward(request, response);
    }
}
