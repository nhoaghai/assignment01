/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Purchase;

/**
 *
 * @author HOANG HAI
 */
public class PurchaseDao extends DBContext {

    public int createPurchase(int userId, double total, String date) {
        String sql = "insert into purchase(user_id, total, date) values(?, ?, ?)";
        int generatedId = -1; // Biến để lưu trữ ID được tạo tự động

        try (PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, userId);
            st.setDouble(2, total);
            st.setString(3, date);
            st.executeUpdate();

            ResultSet generatedKeys = st.getGeneratedKeys();

            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1); // Lấy ID từ ResultSet
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public void updatePurchase(double total, int purchaseId) {
        String sql = " update purchase set total = ? where purchase_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, total);
            ps.setInt(2, purchaseId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Purchase getPurchaseById(String id) {
        String sql = "select * from purchase where purchase_id = ?";
        UserDao u = new UserDao();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Purchase(rs.getInt("purchase_id"),
                        u.getUserByID(rs.getString("user_id")),
                        rs.getDouble("total"),
                        rs.getString("date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Purchase> getAllPurchaseByUserId(String id) {
        List<Purchase> list = new ArrayList<>();
        String sql = "select * from purchase where user_id = ?";
        UserDao u = new UserDao();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Purchase(rs.getInt("purchase_id"),
                        u.getUserByID(rs.getString("user_id")),
                        rs.getDouble("total"),
                        rs.getString("date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Purchase> getAllPurchase() {
        List<Purchase> list = new ArrayList<>();
        String sql = "select * from purchase";
        UserDao u = new UserDao();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Purchase(rs.getInt("purchase_id"),
                        u.getUserByID(rs.getString("user_id")),
                        rs.getDouble("total"),
                        rs.getString("date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void deletePurchaseByUserId(String pid) {
        String sql = "delete from purchase where user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
