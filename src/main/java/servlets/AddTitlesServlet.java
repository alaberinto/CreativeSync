/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;
import services.TitleService;

/**
 *
 * @author 731866
 */
public class AddTitlesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        AccountService as = new AccountService();
        getServletContext().getRequestDispatcher("/WEB-INF/AddTitles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        TitleService ts = new TitleService();
        String name = request.getParameter("name");

        String priority = request.getParameter("priority");
        Short pri = Short.parseShort(priority);
        String designInfo = request.getParameter("info");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date(System.currentTimeMillis());
        

        try {
            String date = request.getParameter("date");
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            ts.insert(null, name, startDate, endDate, new Short("1"), pri, designInfo);
        } catch (ParseException ex) {
            Logger.getLogger(AddTitlesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/AddTitles.jsp").forward(request, response);


    }

}
