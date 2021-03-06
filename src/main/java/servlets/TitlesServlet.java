package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Genre;
import models.Title;
import services.AccountService;
import services.GenreService;
import services.TitleService;
import viewModels.TitlesView;

/**
 * A controller servlet to manage actions related to the Title object. 
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
public class TitlesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TitleService ts = new TitleService();
        GenreService gs = new GenreService();

        //Get user from session
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        
        String searchFilter = request.getParameter("searchBar");

        //Get titles available to that user
        ArrayList<TitlesView> assignedTitles = ts.getAssignedTitles(user, searchFilter);
        request.setAttribute("assignedTitles", assignedTitles);

        if(assignedTitles == null || user.getPosition().getPositionId() != 4) {
            request.setAttribute("unassignedTitles", ts.getUnassignedTitles(user, assignedTitles, searchFilter));
        }

        ArrayList<Genre> genres = gs.getAllGenres();
        request.setAttribute("genres", genres);
        
        request.setAttribute("goodFeedback", session.getAttribute("goodFeedback"));
        session.setAttribute("goodFeedback", null);

        //Forward
        request.setAttribute("searchBar", searchFilter);
        getServletContext().getRequestDispatcher("/WEB-INF/Titles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        TitleService ts = new TitleService();

        String action = (String) request.getParameter("action");

        if (action.equals("addTitle")) {
            response.sendRedirect("AddTitle");
        }
        else if(action.equals("compTitle")) {
            ArrayList<TitlesView> completed = ts.getCompleteTitles();
            
            request.setAttribute("completed", completed);
            getServletContext().getRequestDispatcher("/WEB-INF/Titles.jsp").forward(request, response);
        }
    }
}
