/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LoginDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author GIA KHANH
 */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie ar[] = request.getCookies();
        for (Cookie o : ar) {
            if (o.getName().equals("uC")) {
                request.setAttribute("userName", o.getValue());
            }
            if (o.getName().equals("pC")) {
                request.setAttribute("password", o.getValue());
            }
            if (o.getName().equals("rem")) {
                request.setAttribute("rem", o.getValue());
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        LoginDao loginDao = new LoginDao();
//        Users checkUser = loginDao.login(new Users(username, password));
//
//        if (checkUser == null) {
//            request.setAttribute("error", "Username or password wrong!");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        } else {
//            HttpSession session = request.getSession();
//            session.setAttribute("acc", checkUser);
//            response.sendRedirect("home");
//        }
        String usernameLogin = request.getParameter("userName");
        String passwordLogin = request.getParameter("password");
        String rem = request.getParameter("remember");
        LoginDao ud = new LoginDao();
        Users u = new Users(usernameLogin, passwordLogin);
        int countOrder = 0;
        if (ud.login(u) != null) {
            HttpSession session = request.getSession();
            session.setAttribute("acc", ud.login(u));
            session.setAttribute("countOrder", countOrder);
            if (rem != null && rem.equals("1")) {
                Cookie user = new Cookie("uC", usernameLogin);
                Cookie pass = new Cookie("pC", passwordLogin);
                Cookie remC = new Cookie("rem", rem);
                //set time cho cookie
                user.setMaxAge(30);
                pass.setMaxAge(30);
                remC.setMaxAge(30);

                //luu vao browser
                response.addCookie(user);
                response.addCookie(pass);
                response.addCookie(remC);
            } else {
                Cookie user = new Cookie("uC", usernameLogin);
                Cookie pass = new Cookie("pC", passwordLogin);
                Cookie remC = new Cookie("rem", rem);

                //set time cho cookie
                user.setMaxAge(0);
                pass.setMaxAge(0);
                remC.setMaxAge(0);
                //luu vao browser
                response.addCookie(user);
                response.addCookie(pass);
                response.addCookie(remC);
            }

            response.sendRedirect("home");
        } else {
            request.setAttribute("error", "Username or password incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
