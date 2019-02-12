package servlets;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Title;

/**
 *
 * @author Mason
 */
public class TitleDetailedServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Title title = (Title) session.getAttribute("title");
        
        Date startDate = title.getStartDate();
        Date endDate = title.getEndDate();
        
        request.setAttribute("titlename", title.getName());
        request.setAttribute("startdate", title.getStartDate());
        request.setAttribute("enddate", title.getEndDate());
        request.setAttribute("datepercentage", getPercentageLeft(startDate, endDate));
        
       getServletContext().getRequestDispatcher("/WEB-INF/TitleDetailed.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    public static int getPercentageLeft(Date start, Date end) {
        long now = System.currentTimeMillis();
        long s = start.getTime();
        long e = end.getTime();
        if (s >= e || now >= e) {
            return 0;
        }
        if (now <= s) {
            return 100;
        }
        return (100) - ((int) ((e - now) * 100 / (e - s)));
    }
}
