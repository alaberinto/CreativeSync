package servlets;

import java.io.IOException;
import java.util.ArrayList;
import models.Language;
import models.Location;
import models.Position;
import models.Genre;
import models.Account;
import services.LanguageService;
import services.LocationService;
import services.PositionService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;import services.AccountService;
;
import services.GenreService;
import services.TitleService;

/**
 *
 * @author Mason
 */
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        LocationService ls = new LocationService();
        LanguageService lanS = new LanguageService();
        PositionService ps = new PositionService();
        GenreService gs = new GenreService();

        ArrayList<Location> locations = ls.getAllLocations();
        ArrayList<Language> languages = lanS.getAllLanguages();
        ArrayList<Position> positions = ps.getCreatablePositions((Account) session.getAttribute("user"));
        ArrayList<Genre> genres = gs.getAllGenres();

        request.setAttribute("locations", locations);
        request.setAttribute("languages", languages);
        request.setAttribute("positions", positions);
        request.setAttribute("genres", genres);

        getServletContext().getRequestDispatcher("/WEB-INF/AddUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AccountService as = new AccountService();
                
//      File file = uploadItems.get(0).write(file)
        String fName = request.getParameter("firstName");
        String lName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rate = request.getParameter("rate");
        String countryId = request.getParameter("country");
        String[] languageId = request.getParameterValues("language");
        String positionId = request.getParameter("position");
        String[] genres = request.getParameterValues("genres");
        
        String feedback = as.insert(fName, lName, email, rate, password, countryId, languageId, positionId, genres);
        if(feedback != null) {
            request.setAttribute("badFeedback", feedback);
            doGet(request, response);
        }
        else {
            request.setAttribute("goodFeedback", "User Created Successfully!");
            response.sendRedirect("Users");
        }
        
    }
}
