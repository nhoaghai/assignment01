package controller;

import dal.OrdersDao;
import dal.ProductDao;
import dal.PurchaseDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Map.Entry;
import model.Orders;
import model.Purchase;
import model.Users;

/**
 *
 * @author HOANG HAI
 */
public class AddToPurchase extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrdersDao orderDao = new OrdersDao();
        PurchaseDao purchaseDao = new PurchaseDao();
        ProductDao p = new ProductDao();
        //DOC TU SESSION RA GIO HANG TRUOC
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("cart");// luu tam vao session
        int countOrder = 0;
        if (obj != null) {//KIEM TRA XEM CO SP TRONG GIO HANG KO?
            Map<String, Orders> map = (Map<String, Orders>) obj;

            //TAO HOA DON TRUOC, DE LAY DUOC ID BILL
            Purchase purchase = new Purchase();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            purchase.setDate(sdf.format(new Date()).toString());
            purchase.setDate(sdf.format(new java.util.Date()));
            // lay dang nhap
            Users user = (Users) session.getAttribute("acc");
            purchase.setUser(user);

            //XEM THEM O LOP BILLDAO, CACH TRA VE ID TU GEN O SQL
            int purchaseId = purchaseDao.createPurchase(user.getUserId(), 0, purchase.getDate());// save bill trc de lay id

            // Tim mat hang
            double total = 0;//tinh tong gia

            //lap cac phan tu trong map
            for (Entry<String, Orders> entry : map.entrySet()) {
                Orders order = entry.getValue();

                order.setPurchase(purchase);// set bill id vao day
                //luu lai cac mat hang
                orderDao.createOrder(order.getProduct().getProductId(), order.getQuantity(), purchaseId, order.getPrice());
                // tinh tong gia
                total += order.getQuantity() * order.getPrice();

                // update stock product
                int stockP = order.getProduct().getStock()- order.getQuantity();
                order.getProduct().setStock(stockP);
                p.updateQuantityProduct(stockP, order.getProduct().getProductId());
            }

            ///cap nhat lai bill de co tong gia tien
            purchase.setTotal(total);
            purchaseDao.updatePurchase(total, purchaseId);

            // chuyen ve trang thanh cong
            // xoa session gio hang vi da tao don hang thanh cong, giai phong bo nho
            session.removeAttribute("cart");
            session.setAttribute("countOrder", countOrder);
            response.sendRedirect("thankyou.jsp");
        } else {
            // tra ve trang chu neu gio hang rong
            session.setAttribute("countOrder", countOrder);
            response.sendRedirect("home");
        }
    }

}
