/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author HOANG HAI
 */
public class Changepassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("userId");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String renewpassword = request.getParameter("renewpassword");
        UserDao u = new UserDao();
        Users user = u.getUserByID(id);
        if (oldpassword.equals(user.getPassword()) && newpassword.equals(renewpassword) && !oldpassword.equals(newpassword)) {
            u.editUser(user.getUserName(), newpassword, user.getEmail(), user.getFullName(),
                    user.getPhone(), user.getAddress(), user.getImage(), id);
        } else if (oldpassword.equals(newpassword)) {
            request.setAttribute("err", "Old password same with new password");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        } else {
            request.setAttribute("err", "Wrong password or confirm new password");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("acc", u.getUserByID(id));
        response.sendRedirect("home");
    }

}
