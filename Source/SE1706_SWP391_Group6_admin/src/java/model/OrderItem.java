/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class OrderItem {

    private int orderItemId;
    private int proId;
    private String model, proName, proImg;
    private double proPrice;
    private int orderId;
    private int quantity;
    private double orderItemTotal;
    private String content;

    public OrderItem() {
    }

    public OrderItem(int orderItemId, int proId, String model, String proName, String proImg, double proPrice, int orderId, int quantity, double orderItemTotal, String content) {
        this.orderItemId = orderItemId;
        this.proId = proId;
        this.model = model;
        this.proName = proName;
        this.proImg = proImg;
        this.proPrice = proPrice;
        this.orderId = orderId;
        this.quantity = quantity;
        this.orderItemTotal = orderItemTotal;
        this.content = content;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderIteId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOrderItemTotal() {
        return orderItemTotal;
    }

    public void setOrderItemTotal(double orderItemTotal) {
        this.orderItemTotal = orderItemTotal;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //khai bao cac thanh phan xu li db
    Connection cnn;//ket noi
    Statement stm;//thuc thi cac cau lenh
    PreparedStatement pstm;
    ResultSet rs, rs1, rs2;//luu tru va xu li du lieu

    protected void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn == null) {
                System.out.println("Connect Order Item failed!");
            }
        } catch (Exception e) {
        }
    }

    /*
    purpose: return data of an order item
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void getOrderItem(int orderItemId) {
        try {
            connect();
            String select = "SELECT [OrderItem].productId,[OrderItem].quantity,[OrderItem].orderId "
                    + "FROM [OrderItem] WHERE [OrderItem].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderItemId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.orderItemId = orderItemId;
                this.proId = rs.getInt(1);
                this.quantity = rs.getInt(2);
                this.orderId = rs.getInt(3);
            }
            select = "SELECT [Product].model, [Product].name,[Product].sellPrice,[ProductImage].source "
                    + "FROM [Product] JOIN [ProductImage] ON [Product].id = [ProductImage].productId "
                    + "WHERE [Product].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, this.proId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.model = rs.getString(1);
                this.proName = rs.getString(2);
                this.proPrice = rs.getDouble(3);
                this.proImg = rs.getString(4);
            }
            this.orderItemTotal = this.proPrice * this.quantity;
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getOrderItem: " + e.getMessage());
        }
    }

    /*
    purpose: return a list of order items of an order
    author: hieuddhe171241
    date: 24/05/2023
     */
    public List<OrderItem> getListOrderItem(int orderId) {
        List<OrderItem> list = new ArrayList<>();
        try {
            connect();
            String select = "SELECT [OrderItem].id FROM [OrderItem] WHERE [OrderItem].orderId = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                OrderItem oi = new OrderItem();
                oi.getOrderItem(rs.getInt(1));
                list.add(oi);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListOrderItem: " + e.getMessage());
        }
        return list;
    }

    /*
    purpose: return total items sold
    author: hieuddhe171241
    date: 07/06/2023
     */
    public int getTotalItemSold() {
        int sum = 0;
        try {
            connect();
            String select = "SELECT [Order].id FROM [Order] WHERE [Order].statusId in (4, 6, 7)";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int tmp = rs.getInt(1);
                select = "SELECT [OrderItem].quantity FROM [OrderItem] WHERE [OrderItem].orderId = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, tmp);
                rs1 = pstm.executeQuery();
                while (rs1.next()) {
                    sum += rs1.getInt(1);
                }
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getTotalItemSold: " + e.getMessage());
        }
        return sum;
    }

    /*
    purpose: return total items sold by month
    author: hieuddhe171241
    date: 22/07/2023
     */
    public List getTotalItemSoldByMonth(int categoryId) {
        List<Integer> listData = new ArrayList<>();
        try {
            connect();
            for (int i = 1; i < 13; i++) {
                int sum = 0;
                String select = "SELECT [Order].id FROM [Order] "
                        + "WHERE [Order].statusId in (4, 6, 7) "
                        + "AND MONTH(createdAt) = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, i);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int tmp = rs.getInt(1);
                    select = "SELECT [OrderItem].quantity FROM [OrderItem] "
                            + "JOIN [Product] ON [OrderItem].productId = [Product].id "
                            + "WHERE [OrderItem].orderId = ? AND [Product].categoryId = ?";
                    pstm = cnn.prepareStatement(select);
                    pstm.setInt(1, tmp);
                    pstm.setInt(2, categoryId);
                    rs1 = pstm.executeQuery();
                    while (rs1.next()) {
                        sum += rs1.getInt(1);
                    }
                }
                System.out.println(sum);
                listData.add(sum);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getTotalItemSoldByMonth: " + e.getMessage());
        }
        return listData;
    }
}
