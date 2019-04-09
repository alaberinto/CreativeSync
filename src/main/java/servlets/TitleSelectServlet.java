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
 * @author 739604
 */
public class TitleSelectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get freelancers to select
        TitleService ts = new TitleService();
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("feedback_select");

        //get titles for specific user
        ArrayList<TitlesView> titles;
        titles = ts.getTitlesByUserForTitlesJSP(user);
        request.setAttribute("titles", titles);

//        //check if there are any titles
//        if (titles.isEmpty()) {
//            request.setAttribute("titles_filled", 1);
//            request.setAttribute("titles", titles);
//        } else {
//            request.setAttribute("titles_filled", 0);
//            session.invalidate();
//        }
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
