/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDao;
import dal.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author HOANG HAI
 */
public class EditProduct extends HttpServlet {

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
        ProductDao dao = new ProductDao();
        CategoryDao cDao = new CategoryDao();
        Product p = dao.getProductByID(id);
        List<Category> listC = cDao.getAllCategory();

        request.setAttribute("listC", listC);
        request.setAttribute("detail", p);
        request.getRequestDispatcher("editproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("productId");
        String name = request.getParameter("productName");
        String stock = request.getParameter("stock");
        String unitPrice = request.getParameter("unitPrice");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String category = request.getParameter("category");
        ProductDao dao = new ProductDao();
        CategoryDao cDao = new CategoryDao();
        List<Category> listC = cDao.getAllCategory();
        Product p = dao.getProductByID(id);
        if (name.isBlank() || stock.isBlank() || unitPrice.isBlank() || description.isBlank()
                || image.isBlank()) {
            request.setAttribute("error", "Wrong format product");
            request.setAttribute("detail", p);
            request.setAttribute("listC", listC);
            request.getRequestDispatcher("editproduct.jsp").forward(request, response);
        } else {
            dao.editProduct(name, stock, unitPrice, description, image, category, id);
            response.sendRedirect("listP");
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
