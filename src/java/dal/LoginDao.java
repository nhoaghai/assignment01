/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Users;

/**
 *
 * @author HOANG HAI
 */
public class LoginDao extends DBContext {

    public Users login(Users user) {
        String sql = "select * from users where user_name = ? and password = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            System.out.println("rs" + rs);
            while (rs.next()) {
                Users users = new Users(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getInt("role"),
                        rs.getString("image"));
                return users;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Users checkDuplicateUser(Users user) {
        String sql = "select * from users\n"
                + "where user_name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users users = new Users(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getInt("role"),
                        rs.getString("image"));
                return users;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
     public Users checkDuplicatePhone(Users user) {
        String sql = "select * from users\n"
                + "where phone = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getPhone());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users users = new Users(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getInt("role"),
                        rs.getString("image"));
                return users;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
     
      public Users checkDuplicateEmail(Users user) {
        String sql = "select * from users\n"
                + "where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users users = new Users(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getInt("role"),
                        rs.getString("image"));
                return users;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    

    public void register(Users users) {
        String sql = "insert into users(user_name, password, email, full_name, phone, address, image) values(?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, users.getUserName());
            ps.setString(2, users.getPassword());
            ps.setString(3, users.getEmail());
            ps.setString(4, users.getFullName());
            ps.setString(5, users.getPhone());
            ps.setString(6, users.getAddress());
            ps.setString(7, users.getImage());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
