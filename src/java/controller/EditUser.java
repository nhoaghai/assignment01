/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDao;
import dal.LoginDao;
import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Users;

/**
 *
 * @author GIA KHANH
 */
public class EditUser extends HttpServlet {

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
            out.println("<title>Servlet EditUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUser at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("pid");
        UserDao dao = new UserDao();
        CategoryDao cdao = new CategoryDao();
        Users u = dao.getUserByID(id);
        List<Category> listC = cdao.getAllCategory();

        request.setAttribute("listC", listC);
        request.setAttribute("detail", u);
        request.getRequestDispatcher("edituser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginDao loginDao = new LoginDao();
        UserDao dao = new UserDao();

        String idU = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String image = request.getParameter("image");
        String regex = "^0\\d{9}$";
        int id = Integer.parseInt(idU);
        Users user = new Users(id, username, password, email, name, phone, address, 2, image);
        Users u = dao.getUserByID(idU);
        if (loginDao.checkDuplicateUser(user) != null && !username.equals(loginDao.checkDuplicateUser(u).getUserName())) {
            request.setAttribute("error", "Username already exist!");
            request.setAttribute("detail", u);
            request.getRequestDispatcher("edituser.jsp").forward(request, response);
        } else if (loginDao.checkDuplicateEmail(user) != null && !email.equals(loginDao.checkDuplicateUser(u).getEmail())) {
            request.setAttribute("error", "Email already exist!");
            request.setAttribute("detail", u);
            request.getRequestDispatcher("edituser.jsp").forward(request, response);
        } else if (loginDao.checkDuplicatePhone(user) != null && !phone.equals(loginDao.checkDuplicateUser(u).getPhone())) {
            request.setAttribute("error", "Phone already exist!");
            request.setAttribute("detail", u);
            request.getRequestDispatcher("edituser.jsp").forward(request, response);
        } else if (!user.getPhone().matches(regex)) {
            request.setAttribute("error", "Wrong phone format");
            request.setAttribute("detail", u);
            request.getRequestDispatcher("edituser.jsp").forward(request, response);
        } else if (idU.isBlank() || username.isBlank() || password.isBlank() || email.isBlank() || name.isBlank()
                || phone.isBlank() || address.isBlank()) {
            request.setAttribute("error", "Wrong format");
            request.setAttribute("detail", u);
            request.getRequestDispatcher("edituser.jsp").forward(request, response);
        } else {
            dao.editUser(username, password, email, name, phone, address, image, idU);
            response.sendRedirect("listU");
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
