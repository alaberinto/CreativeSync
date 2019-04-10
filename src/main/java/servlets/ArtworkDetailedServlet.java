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

            if (session.getAttribute("position").equals("freelancer")) { //freelancer
                Account user_f = (Account) session.getAttribute("user");
                String owner_s = user_f.getFirstname() + " " + user_f.getLastname();
                request.setAttribute("username_fl", owner_s);
                request.setAttribute("position", "0");
            } else {
                Account user = (Account) session.getAttribute("feedback_select");
                String owner_s = user.getFirstname() + " " + user.getLastname();
                request.setAttribute("username_fl", owner_s);
                request.setAttribute("position", "1");
            }

            request.setAttribute("approve_deny_val", 0); //0 to see the buttons          
            request.setAttribute("title", session.getAttribute("title_select")); //title name            

            //title id retrieval
            String title_select_id_s = (String) session.getAttribute("title_select_id"); //null

            //if there are titles
            if (title_select_id_s != null) {
                int title_select_id = Integer.parseInt(title_select_id_s);

                //get rounds
                ArtworkService as = new ArtworkService();
                List<Artwork> rounds;
                rounds = as.getAllRounds(title_select_id);

                //gets artwork for each round
                List<Artwork> round_art;
                round_art = as.getAllArtworkByTitleId(title_select_id);
                request.setAttribute("round_art", round_art);

                //check if there are any rounds
                if (rounds.isEmpty() == false) {
                    request.setAttribute("rounds_filled", 1);
                    request.setAttribute("rounds", rounds);
                } else {
                    request.setAttribute("rounds_filled", 0);
                }
            } else {
                //if there are no titles     
                request.setAttribute("titles_filled", 0);
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
            Account user = (Account) session.getAttribute("feedback_select");
            String owner_s = user.getFirstname() + " " + user.getLastname();
            request.setAttribute("username_fl", owner_s); // may not be needed, or changed
            request.setAttribute("title", session.getAttribute("title_select"));

            //get comments
            String comment = request.getParameter("comment");
            request.setAttribute("comment", comment);

            //title id retrieval
            String title_select_id_s = (String) session.getAttribute("title_select_id");
            int title_select_id = Integer.parseInt(title_select_id_s);

            //get rounds
            ArtworkService as = new ArtworkService();
            List<Artwork> rounds;
            rounds = as.getAllRounds(title_select_id);
            request.setAttribute("rounds", rounds);

            //changes the approve/deny message when the form is posted
            String approved = request.getParameter("approve");
            String deny = request.getParameter("deny");

            if (approved != null) {
                request.setAttribute("approve_deny_val", 1); //1 if round is approved
            }

            if (deny != null) {
                request.setAttribute("approve_deny_val", 2); //2 if round is denied
            }

            getServletContext().getRequestDispatcher("/WEB-INF/ArtworkDetailed.jsp").forward(request, response);
        } catch (DBException ex) {
            Logger.getLogger(ArtworkDetailedServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
