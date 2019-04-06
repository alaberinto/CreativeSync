/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import models.Account;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;
import services.TitleService;

/**
 *
 * @author 731866
 */
public class AddTitleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        
        ArrayList<Account> leads = as.getActiveLeads();
        ArrayList<Account> coors = as.getActiveCoordinators();
        ArrayList<Account> frees = as.getActiveFreelancers();
        
        request.setAttribute("leads", leads);
        request.setAttribute("coors", coors);
        request.setAttribute("freelancers", frees);
        
        getServletContext().getRequestDispatcher("/WEB-INF/AddTitle.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String titleName = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String priority = request.getParameter("priority");
        int maxNumberOfFreelancers = Integer.parseInt(request.getParameter("numberOfFreelancers"));
        int coordinatorId = Integer.parseInt(request.getParameter("coorId"));
        int leadId = Integer.parseInt(request.getParameter("leadId"));
        String designInfo = request.getParameter("info");
        String[] freelancerIds = request.getParameterValues("freelancers");
        
        
        TitleService ts = new TitleService();
        
        String feedback = ts.insert(titleName, startDate, endDate, priority, designInfo , leadId, coordinatorId, maxNumberOfFreelancers, freelancerIds);
        
        if(feedback != null) {
            request.setAttribute("badFeedback", feedback);
            
            //Set back attributes
            getServletContext().getRequestDispatcher("/WEB-INF/AddTitle.jsp").forward(request, response);
        }
        
        else
            request.setAttribute("goodFeedback", "Title added successfully");
            getServletContext().getRequestDispatcher("/WEB-INF/Titles.jsp").forward(request, response);
    }
}
