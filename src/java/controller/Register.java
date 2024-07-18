/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LoginDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

/**
 *
 * @author GIA KHANH
 */
public class Register extends HttpServlet {

//rs(username, [password], email, [name], phone, [address])\n"
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String image = request.getParameter("image");
        String regex = "^0\\d{9,10}$";
        LoginDao loginDao = new LoginDao();
        Users user = new Users(0, username, password, email, name, phone, address, 2, image);
        if (loginDao.checkDuplicateUser(user) != null) {
            request.setAttribute("error", "Username already exist!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (loginDao.checkDuplicateEmail(user) != null) {
            request.setAttribute("error", "Email already exist!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (loginDao.checkDuplicatePhone(user) != null) {
            request.setAttribute("error", "Phone already exist!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!user.getPhone().matches(regex)) {
            request.setAttribute("error", "Wrong phone format");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            loginDao.register(user);
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
