/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import model.Orders;

/**
 *
 * @author GIA KHANH
 */
public class DeleteOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("key");

        // gio hang co nhieu mat hang
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("cart");// luu tam vao session
        Object objTotal = session.getAttribute("total");
        Object objCountOrder = session.getAttribute("countOrder");
        if (obj != null) {
            Map<String, Orders> map = (Map<String, Orders>) obj;
            double total = (double) objTotal;
            int countOrder = (int) objCountOrder;
            countOrder--;
            total -= map.get(key).getPrice() * map.get(key).getQuantity();
            map.remove(key);
            // update lai vao session
            session.setAttribute("cart", map);
            // update lai total price
            session.setAttribute("total", total);
            session.setAttribute("countOrder", countOrder);
            if (map.isEmpty()) {
                session.removeAttribute("cart");
            }
        }

        // chuyen ve trang gio hang
        response.sendRedirect("cart.jsp");
    }

}
