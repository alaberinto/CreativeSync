/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.DBException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import services.AccountService;

/**
 *
 * @author 739604
 */
@WebServlet(name = "FeedbackSelectServlet", urlPatterns = {"/FeedbackSelect"})
public class FeedbackSelectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get freelancers to select
        AccountService as = new AccountService();

        List<Account> freelancers;
        freelancers = as.getAllUsers();
        request.setAttribute("freelancers", freelancers);

        HttpSession session = request.getSession();
        Account user_f = (Account) session.getAttribute("user");

        int pos_p_i = user_f.getPosition().getPositionId();

        // for freelancer view only
        if (pos_p_i == 4) {
            getServletContext().getRequestDispatcher("/WEB-INF/TitleSelect.jsp").forward(request, response);
            session.setAttribute("status", "freelancer");
        } else {
            session.setAttribute("status", "designlead");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/FeedbackSelect.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            AccountService as = new AccountService();

            //selection page
            String feedback_select = request.getParameter("feedback_select");
            Account user = as.getUserByFirstname(feedback_select);

            if (feedback_select != null) {
                session.setAttribute("feedback_select", user);
//            getServletContext().getRequestDispatcher("/WEB-INF/ArtworkDetailed.jsp").forward(request, response);
            }

            getServletContext().getRequestDispatcher("/WEB-INF/FeedbackSelect.jsp").forward(request, response);
        } catch (DBException ex) {
            Logger.getLogger(FeedbackSelectServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
