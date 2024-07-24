/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import model.Orders;

/**
 *
 * @author HOANG HAI
 */
public class ProcessCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("cart");
        double total = 0;
        Map<String, Orders> cart = (Map<String, Orders>) obj;
        String tquantity = request.getParameter("quantity");
        String tid = request.getParameter("id");
        int quantity = Integer.parseInt(tquantity);
        Object objCountOrder = session.getAttribute("countOrder");
        int countOrder = (int) objCountOrder;
        if (quantity == 0) {
            cart.remove(tid);
            countOrder--;
            for (Map.Entry<String, Orders> entry : cart.entrySet()) {
                total += entry.getValue().getQuantity() * entry.getValue().getPrice();
            }
            session.setAttribute("countOrder", countOrder);
        } else if (quantity > cart.get(tid).getProduct().getStock()) {
            request.setAttribute("error", cart.get(tid).getProduct().getProductName()
                    + " only has " + cart.get(tid).getProduct().getStock()+ " left.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        } else {
            Orders o = cart.get(tid);
            o.setQuantity(quantity);
            for (Map.Entry<String, Orders> entry : cart.entrySet()) {
                total += entry.getValue().getQuantity() * entry.getValue().getPrice();
            }
        }
        if (cart.isEmpty()) {
            session.removeAttribute("total");
            session.removeAttribute("cart");
        } else {
            session.setAttribute("total", total);
            session.setAttribute("cart", cart);
        }
        response.sendRedirect("cart.jsp");
    }
}
