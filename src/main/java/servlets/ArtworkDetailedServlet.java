package servlets;

import dataaccess.DBException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Artwork;
import services.ArtworkService;

/**
 *
 * @author Matthew
 */
public class ArtworkDetailedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();

            /*
            1 = admin
            2 = coordinator
            3 = design lead
            4 = freelancer
             */
            Account user = (Account) session.getAttribute("user");
            int position = user.getPosition().getPositionId();

            if (position == 4) { //freelancer       
                request.setAttribute("position", "0");
            } else {
                request.setAttribute("position", "1");
            }

            request.setAttribute("status", 0); //0 to see the buttons    

            //title id retrieval            
            int titleId = (int) session.getAttribute("titleId");

            //get rounds
            ArtworkService as = new ArtworkService();
            List<Artwork> rounds;
            rounds = as.getAllRounds(titleId);

            //gets artwork for each round
            List<Artwork> roundArt;
            roundArt = as.getAllArtworkByTitleId(titleId);
            request.setAttribute("roundArt", roundArt);

            //check if there are any rounds & artwork
            if (rounds.isEmpty() == false) {
                request.setAttribute("roundsFilled", 1);
                request.setAttribute("rounds", rounds);
            } else {
                request.setAttribute("roundsFilled", 0);
            }

            getServletContext().getRequestDispatcher("/WEB-INF/ArtworkDetailed.jsp").forward(request, response);
        } catch (DBException ex) {
            Logger.getLogger(ArtworkDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();

            //get comments
            String comment = request.getParameter("comment");
            request.setAttribute("comment", comment);

            //title id retrieval
            int titleId = (int) session.getAttribute("titleId");

            //get rounds
            ArtworkService as = new ArtworkService();
            List<Artwork> rounds;
            rounds = as.getAllRounds(titleId);
            request.setAttribute("rounds", rounds);

            //changes the approve/deny message when the form is posted
            String approved = request.getParameter("approve");
            String deny = request.getParameter("deny");

            if (approved != null) {
                request.setAttribute("status", 1); //1 if round is approved
            }

            if (deny != null) {
                request.setAttribute("status", 2); //2 if round is denied
            }

            getServletContext().getRequestDispatcher("/WEB-INF/ArtworkDetailed.jsp").forward(request, response);
        } catch (DBException ex) {
            Logger.getLogger(ArtworkDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
