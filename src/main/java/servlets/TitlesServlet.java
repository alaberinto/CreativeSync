package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Genre;
import models.Title;
import services.GenreService;
import services.TitleService;
import viewModels.TitlesView;

/**
 *
 * @author Mason
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

        //Get titles available to that user
        ArrayList<TitlesView> titles = ts.getTitlesByUserForTitlesJSP(user);
        request.setAttribute("titles", titles);

        //Get titles
        ArrayList<Genre> genres = gs.getAllGenres();
        request.setAttribute("genres", genres);

        //Forward
        getServletContext().getRequestDispatcher("/WEB-INF/Titles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        TitleService ts = new TitleService();

        String action = (String) request.getParameter("action");

        if (action.equals("addTitle")) {
            //getServletContext().getRequestDispatcher("/WEB-INF/AddTitle.jsp").forward(request, response);
            response.sendRedirect("AddTitle");
        } else if (false) {
            //String title_id = request.getParameter("title_id");
            Title title = ts.getTitleById(1);
            session.setAttribute("title", title);
        }
    }
}
