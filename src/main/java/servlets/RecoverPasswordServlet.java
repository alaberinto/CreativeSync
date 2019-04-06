/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

/**
 *
 * @author Mason
 */
public class RecoverPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String newPass = request.getParameter("newPassword");
        String newPassConf = request.getParameter("newPasswordConf");

        if (newPass.equals(newPassConf)) {
            AccountService as = new AccountService();

            if (as.updatePassword(newPass, (String) session.getAttribute("recoverEmail")) == null) {
                request.setAttribute("goodFeedback", "Password Successfully Reset");
                getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
            } else {
                request.setAttribute("badFeedback", "An Error Occured!");
                getServletContext().getRequestDispatcher("/WEB-INF/RecoverPassword.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("badFeedback", "Passwords Do Not Match!");
            getServletContext().getRequestDispatcher("/WEB-INF/RecoverPassword.jsp").forward(request, response);
        }
    }
}
