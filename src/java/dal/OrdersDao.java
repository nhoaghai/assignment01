/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Orders;

/**
 *
 * @author HOANG HAI
 */
public class OrdersDao extends DBContext {
    public void createOrder(int productId, int quantity, int purchaseId, double price) {
        String sql = " insert into orders(product_id, quantity, purchase_id, price)\n"
                + "  values(?, ?, ?, ?)";
         try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.setInt(2, quantity);
            st.setInt(3, purchaseId);
            st.setDouble(4, price);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Orders> getAllOrderByPuchaseId(String id){
        String sql = "select * from orders where purchase_id = ?";
        List<Orders> list = new ArrayList<>();
        ProductDao p = new ProductDao();
        PurchaseDao pu = new PurchaseDao();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(new Orders(rs.getInt("order_id"), 
                        p.getProductByID(rs.getString("product_id")),
                        rs.getInt("quantity"),
                        rs.getDouble("price"), 
                        pu.getPurchaseById(rs.getString("purchase_id"))));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void deleteOrdersByProductId(String pid) {
        String sql = "delete from orders\n"
                + "  where product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   
    public void deleteOrdersByPurchaseId(int pid) {
        String sql = "delete from orders\n"
                + "  where purchase_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}