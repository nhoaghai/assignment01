/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LoginDao;
import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
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
public class InforUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InforUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InforUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginDao loginDao = new LoginDao();
        UserDao ud = new UserDao();
        String idU = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String image = request.getParameter("image");
        String regex = "^0\\d{9}$";

        Users user = new Users(0, name, phone, email, name, phone, address, 2, image);
        Users u = ud.getUserByID(idU);
        if (loginDao.checkDuplicateEmail(user) != null && !email.equals(loginDao.checkDuplicateEmail(u).getEmail())) {
            request.setAttribute("error", "Email already exist!");
            request.getRequestDispatcher("account.jsp").forward(request, response);
        } else if (loginDao.checkDuplicatePhone(user) != null && !phone.equals(loginDao.checkDuplicateUser(u).getPhone())) {
            request.setAttribute("error", "Phone already exist!");
            request.getRequestDispatcher("account.jsp").forward(request, response);
        } else if (!user.getPhone().matches(regex)) {
            request.setAttribute("error", "Wrong phone format");
            request.getRequestDispatcher("account.jsp").forward(request, response);
        } else if (name.isBlank() || email.isBlank() || address.isBlank() || phone.isBlank()) {
            request.setAttribute("error", "Wrong format");
            request.getRequestDispatcher("account.jsp").forward(request, response);
        } else {
            ud.updateUser(name, email, address, phone, image, idU);
            HttpSession session = request.getSession();
            session.setAttribute("acc", ud.getUserByID(idU));
            response.sendRedirect("account.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
