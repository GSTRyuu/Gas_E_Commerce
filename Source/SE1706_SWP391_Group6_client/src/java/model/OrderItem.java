/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
                System.out.println("Connect OrderItem failed!");
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
                this.proId = rs.getInt(1);
                this.quantity = rs.getInt(2);
                this.orderId = rs.getInt(3);
            }
            select = "SELECT [Product].name,[Product].sellPrice,[ProductImage].source "
                    + "FROM [Product] JOIN [ProductImage] ON [Product].id = [ProductImage].productId "
                    + "WHERE [Product].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, this.proId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.proName = rs.getString(1);
                this.proPrice = rs.getDouble(2);
                this.proImg = rs.getString(3);
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
            String select = "SELECT [OrderItem].id,[OrderItem].productId,[OrderItem].quantity,[OrderItem].content "
                    + "FROM [OrderItem] WHERE [OrderItem].orderId = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int orderItemId = rs.getInt(1);
                int proId = rs.getInt(2);
                int quantity = rs.getInt(3);
                String content = rs.getString(4);
                String proImg;
                String proName;
                String model;
                double proPrice;
                select = "SELECT [Product].name,[Product].model,[Product].sellPrice,[ProductImage].source "
                        + "FROM [Product] JOIN [ProductImage] ON [Product].id = [ProductImage].productId "
                        + "WHERE [Product].id = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, proId);
                rs1 = pstm.executeQuery();
                if (rs1.next()) {
                    proName = rs1.getString(1);
                    model = rs1.getString(2);
                    proPrice = rs1.getDouble(3);
                    proImg = rs1.getString(4);
                    list.add(new OrderItem(orderItemId, proId, model, proName, proImg, proPrice, orderId, quantity, quantity * proPrice, content));
                }
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListOrderItem: " + e.getMessage());
        }
        return list;
    }

    /*
    purpose: add an order item with quantity to an order
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void addOrderItem(int userId, int proId, int quantity) {
        try {
            connect();
            int orderId = 0;
            double subTotal = 0, proPrice = 0;
            boolean exist = false;
            String select = "SELECT [Order].id,[Order].subTotal FROM [Order] WHERE [Order].userId = ? AND [Order].statusId = 0";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();
            if (!rs.next()) {
                String insert = "INSERT INTO [Order]([userId]) "
                        + "VALUES (?) ";
                pstm = cnn.prepareStatement(insert);
                pstm.setInt(1, userId);
                pstm.execute();
                select = "SELECT [Order].id FROM [Order] WHERE [Order].userId = ? AND [Order].statusId = 0";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, userId);
                rs1 = pstm.executeQuery();
                while (rs1.next()) {
                    orderId = rs1.getInt(1);
                }
            } else {
                orderId = rs.getInt(1);
                subTotal = rs.getDouble(2);
            }
            select = "SELECT [OrderItem].productId,[OrderItem].quantity FROM [OrderItem] WHERE [OrderItem].orderId = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == proId) {
                    exist = true;
                    quantity += rs.getInt(2);
                    select = "SELECT COUNT(productId) FROM [ProductInStock] "
                            + "WHERE [ProductInStock].productId = ? AND [ProductInStock].warrantyExp IS NULL";
                    pstm = cnn.prepareStatement(select);
                    pstm.setInt(1, proId);
                    rs1 = pstm.executeQuery();
                    if (rs1.next()) {
                        if (quantity > rs1.getInt(1)) {
                            quantity = rs1.getInt(1);
                        }
                    }
                }
            }
            if (!exist) {
                String insert = "INSERT INTO [OrderItem]([productId],[orderId],[quantity]) "
                        + "VALUES (?,?,?)";
                pstm = cnn.prepareStatement(insert);
                pstm.setInt(1, proId);
                pstm.setInt(2, orderId);
                pstm.setInt(3, quantity);
                pstm.execute();
            } else {
                String update = "UPDATE [OrderItem] SET [OrderItem].quantity = ? WHERE [OrderItem].productId = ?";
                pstm = cnn.prepareStatement(update);
                pstm.setInt(1, quantity);
                pstm.setInt(2, proId);
                pstm.execute();
            }
            select = "SELECT [sellPrice] FROM [Product] WHERE [Product].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, proId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                proPrice = rs.getDouble(1);
            }
            String update = "UPDATE [Order] SET [Order].subTotal = ? WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setDouble(1, proPrice * quantity + subTotal);
            pstm.setInt(2, orderId);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("addOrderItem: " + e.getMessage());
        }
    }

    /*
    purpose: delete a single order item of an order
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void deleteSingleOrderItem(int orderItemId) {
        OrderItem orderItem = new OrderItem();
        try {
            connect();
            double subTotal = 0;
            orderItem.getOrderItem(orderItemId);
            String select = "SELECT [Order].subTotal FROM [Order] WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderItem.getOrderId());
            rs = pstm.executeQuery();
            while (rs.next()) {
                subTotal = rs.getDouble(1);
            }
            String update = "UPDATE [Order] SET [Order].subTotal = ? "
                    + "WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setDouble(1, (subTotal - orderItem.getOrderItemTotal()));
            pstm.setInt(2, orderItem.getOrderId());
            pstm.execute();
            String delete = "DELETE FROM [OrderItem] WHERE [OrderItem].id = ?";
            pstm = cnn.prepareStatement(delete);
            pstm.setInt(1, orderItemId);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("deleteSingleOrderItem: " + e.getMessage());
        }
    }

    /*
    purpose: delete multiple order items of an order
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void deleteOrderItems(String[] orderItemId) {
        OrderItem orderItem = new OrderItem();
        try {
            connect();
            double subTotal = 0;
            for (String i : orderItemId) {
                orderItem.getOrderItem(Integer.parseInt(i));
                String select = "SELECT [Order].subTotal FROM [Order] WHERE [Order].id = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, orderItem.getOrderId());
                rs = pstm.executeQuery();
                while (rs.next()) {
                    subTotal = rs.getDouble(1);
                }
                String update = "UPDATE [Order] SET [Order].subTotal = ? "
                        + "WHERE [Order].id = ?";
                pstm = cnn.prepareStatement(update);
                pstm.setDouble(1, subTotal - orderItem.getOrderItemTotal());
                pstm.setInt(2, orderItem.getOrderId());
                pstm.execute();
                String delete = "DELETE FROM [OrderItem] WHERE [OrderItem].id = ?";
                pstm = cnn.prepareStatement(delete);
                pstm.setInt(1, Integer.parseInt(i));
                pstm.execute();
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("deleteOrderItems: " + e.getMessage());
        }
    }
}
