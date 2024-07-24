/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Users;

/**
 *
 * @author HOANG HAI
 */
public class UserDao extends DBContext {

    public Users getUserByID(String id) {
        String sql = "select * from users where user_id = ?"
                + "";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return (new Users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9)));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void deleteUser(String pid) {
        String sql = "delete from users where user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertUser(String username, String password,
            String email, String name, String phone,
            String address, String image) {
        String sql = "insert into users(user_name, password, email, full_name, phone, address, image) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, name);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7, image);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editUser(String userName, String password,
            String email, String fullName, String phone,
            String address, String image, String userId) {
        String sql = "update users set user_name = ?, password = ?, email = ?, full_name = ?, phone = ?, address = ?, image = ? where user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, fullName);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7, image);
            ps.setString(8, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateUser(String fullName,
            String email, String address,
            String phone, String image,
            String userId) {
        String sql = "update users set full_name = ?, email = ?, address = ?, phone = ?, image = ? where user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, image);
            ps.setString(6, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getTotalUser() {
        String sql = "select count (*) from users";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Users> pagingUser(int index) {
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * from users order by user_id asc limit 4 offset ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 4);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
     public Users checkDuplicateUserName(String username) {
        String sql = "select * from users where user_name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users users = new Users(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("name"),
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
}
