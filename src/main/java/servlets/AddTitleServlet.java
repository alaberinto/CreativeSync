/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import java.io.File;
import models.Account;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Genre;
import services.AccountService;
import services.GenreService;
import services.TitleService;

/**
 * AddTitleServlet is a class managing the content displayed on the page AddTitle.JSP.
 *
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 */
public class AddTitleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        GenreService gs = new GenreService();
        
        ArrayList<Account> leads = as.getActiveLeads();
        ArrayList<Account> coors = as.getActiveCoordinators();
        ArrayList<Account> frees = as.getActiveFreelancers();
        ArrayList<Genre> genres = gs.getAllGenres();
        
        request.setAttribute("genres", genres);
        request.setAttribute("leads", leads);
        request.setAttribute("coors", coors);
        request.setAttribute("freelancers", frees);
        if(request.getParameter("numberOfFreelancers") == null) {
            request.setAttribute("numberOfFreelancers", "1");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/AddTitle.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String titleName = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String priority = request.getParameter("priority");
        String maxNumberOfFreelancers = request.getParameter("numberOfFreelancers");
        String coordinatorId = request.getParameter("coorId");
        String leadId = request.getParameter("leadId");
        String designInfo = request.getParameter("info");
        String[] freelancerIds = request.getParameterValues("freelancers");
        String[] genreIds = request.getParameterValues("genres");
        
        
        TitleService ts = new TitleService();
        
        String feedback = ts.insert(titleName, startDate, endDate, priority, designInfo , leadId, coordinatorId, maxNumberOfFreelancers, freelancerIds, genreIds);
        
        if(feedback != null) {
            request.setAttribute("name", titleName);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            
            if(priority != null) {
                request.setAttribute("checked", "checked");
            } else if (priority == null) {
                request.setAttribute("checked", " ");
            }
            
            request.setAttribute("numberOfFreelancers", maxNumberOfFreelancers);
            request.setAttribute("coorId", coordinatorId);
            request.setAttribute("leadId", leadId);
            request.setAttribute("info", designInfo);
            request.setAttribute("freeIds", freelancerIds);
            request.setAttribute("genreIds", genreIds);
            
            
            request.setAttribute("badFeedback", feedback);
            doGet(request, response);
        }
        
        else {
            HttpSession session = request.getSession();
            session.setAttribute("goodFeedback", "Title added successfully!");
            response.sendRedirect("Titles");
        }
    }
}
