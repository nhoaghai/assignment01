/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author HOANG HAI
 */
public class Purchase {
    private int purchaseId;
    private Users user;
    private double total;
    private String date;

    public Purchase() {
    }

    public Purchase(int purchaseId, Users user, double total, String date) {
        this.purchaseId = purchaseId;
        this.user = user;
        this.total = total;
        this.date = date;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Purchase{" + "id=" + purchaseId + ", user=" + user + ", total=" + total + ", date=" + date + '}';
    }
}
   

   