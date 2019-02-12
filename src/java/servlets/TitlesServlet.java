package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Title;
import services.TitleService;

/**
 *
 * @author Mason
 */
public class TitlesServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/WEB-INF/Titles.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session = request.getSession();
            TitleService ts = new TitleService();
        
            //String title_id = request.getParameter("title_id");
            Title title = ts.getTitleById(1);
            session.setAttribute("title", title);
            
           response.sendRedirect("TitleDetailed");
    }
}
