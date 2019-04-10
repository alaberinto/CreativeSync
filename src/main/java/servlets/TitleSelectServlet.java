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
import models.Title;
import services.TitleService;
import viewModels.TitlesView;

/**
 *
 * @author Matthew
 */
public class TitleSelectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get freelancers to select
        TitleService ts = new TitleService();
        HttpSession session = request.getSession();
        ArrayList<TitlesView> titles;

        //freelancer
        if (session.getAttribute("status").equals("freelaner")) {
            Account user_f = (Account) session.getAttribute("user");
            titles = ts.getTitlesByUserForTitlesJSP(user_f);
            request.setAttribute("titles", titles);
        } else {
            Account user = (Account) session.getAttribute("feedback_select");
            titles = ts.getTitlesByUserForTitlesJSP(user);
            request.setAttribute("titles", titles);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/TitleSelect.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        TitleService ts = new TitleService();
        //selection page
        String title_select = request.getParameter("title_select");
        String title_select_id = request.getParameter("title_select_id");
        Title title = ts.getTitleByName(title_select);

        int title_id_i = Integer.parseInt(title_select_id);
        Title title_id_t = ts.getTitleById(title_id_i);
        String title_id = title_id_t.getTitleId().toString();

        if (title_select != null) {
            session.setAttribute("title_select", title);
            session.setAttribute("title_select_id", title_id);
//            getServletContext().getRequestDispatcher("/WEB-INF/ArtworkDetailed.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/TitleSelect.jsp").forward(request, response);
    }

}
