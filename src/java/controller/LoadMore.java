/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDao;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import model.Product;
import model.Users;

/**
 * @author GIA KHANH
 */
public class LoadMore extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String amount = request.getParameter("exits");
        int iamount = Integer.parseInt(amount);
        ProductDao dao = new ProductDao();
        List<Product> list = dao.getNext4Product(iamount);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("acc");
        Users acc = (Users) obj;
        for (Product o : list) {
            out.println("                        <div class=\"product col mb-5\">\n"
                    + "                            <div class=\"card h-100\">\n"
                    + "                                <!-- Product image-->\n"
                    + "                                <img class=\"card-img-top\" src=\"" + o.getImage() + "\" alt=\"...\" />\n"
                    + "                                <!-- Product details-->\n"
                    + "                                <div class=\"card-body p-4\">\n"
                    + "                                    <div class=\"text-center\">\n"
                    + "                                        <!-- Product name-->\n"
                    + "                                        <h5 class=\"fw-bolder\">" + o.getProductName()+ "</h5>\n"
                    + "                                        <!-- Product price-->\n"
                    + "                                        $" + o.getUnitPrice()+ "\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                                <!-- Product actions-->\n"
                    + "                                <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent d-flex justify-content-between\">\n"
                    + "                                    <div class=\"text-center\">\n"
                    + "                                        <a class=\"btn btn-outline-success\" href=\"detail?pid=" + o.getProductId()+ "\">View detail</a>\n"
                    + "                                    </div>\n");
            if ((o.getStock()== 0)) {
                out.println("                                    <div class=\"text-center\">\n"
                        + "                                        <a class=\"btn btn-outline-danger\">Out of stock</a>\n"
                        + "                                    </div>\n");
            } else if ((o.getStock()> 0) && ((acc == null) || (acc.getRole() == 2))) {
                out.println("                                    <div class=\"text-center\">\n"
                        + "                                        <a class=\"btn btn-outline-info\" href=\"add-to-cart?id=" + o.getProductId()+ "\">Add to cart</a>\n"
                        + "                                    </div>\n");
            }
            out.println("                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
