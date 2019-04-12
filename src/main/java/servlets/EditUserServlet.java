/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import models.Language;
import models.Location;
import models.Position;
import services.AccountService;
import services.GenreService;
import services.LanguageService;
import services.LocationService;
import services.PositionService;
import viewModels.UsersView;

/**
 *
 * @author 759388
 */
public class EditUserServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        GenreService gs = new GenreService();
        LocationService ls = new LocationService();
        PositionService ps = new PositionService();
        LanguageService langs = new LanguageService();

        String acc = request.getParameter("name");
        try {

            UsersView uv = as.getUsersViewMyAccount(acc);
            Account ac = uv.getUser();
            request.setAttribute("myUser", ac);
            ArrayList<Genre> genres = gs.getAllGenres();
            ArrayList<Location> loc = ls.getAllLocations();
            ArrayList<Language> lang = langs.getAllLanguages();
            ArrayList<Position> pos = ps.getCreatablePositions(ac);
            request.setAttribute("genres", genres);
            request.setAttribute("locations", loc);
            request.setAttribute("languages", lang);
            request.setAttribute("positions", pos);

        } catch (Exception ex) {
            request.setAttribute("badFeedback", "Error getting user");
            getServletContext().getRequestDispatcher("/WEB-INF/Users.jsp").forward(request, response);

        }

        getServletContext().getRequestDispatcher("/WEB-INF/EditUser.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
            String acc = request.getParameter("myUser");
        Integer account = Integer.parseInt(acc);
        Account ac = as.getUserById(account);
        String fName = request.getParameter("firstname");
        String lName = request.getParameter("lastname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String rate = request.getParameter("rate");
        String countryId = request.getParameter("country");
        String[] languageId = request.getParameterValues("language");
        String positionId = request.getParameter("position");
        String[] genres = request.getParameterValues("genres");
        try {
            as.editUser(ac, fName, lName, email, rate, genres, countryId, languageId, positionId,password);

        } catch (Exception ex) {
            request.setAttribute("badFeedback", "Error editing user");
            doGet(request,response);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/EditUser.jsp").forward(request, response);
    }

}
