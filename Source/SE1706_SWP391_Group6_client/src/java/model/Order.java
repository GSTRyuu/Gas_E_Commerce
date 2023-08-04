/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import dal.DBContext;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class Order {

    private int orderId, userId;
    private String firstname, lastname, mobile;
    private int statusId;
    private String status, discountCode;
    private double discount, total, tax, shipping, grandtotal;
    private String line1, line2, city, province;
    private int countryId;
    private String country, createdAt, updatedAt, content, payment;

    public Order() {
    }

    public Order(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    public Order(String firstname, String lastname, String mobile, String line1, String line2, String city, String province, int countryId, String country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.province = province;
        this.countryId = countryId;
        this.country = country;
    }

    public Order(int orderId, int userId, String firstname, String lastname, String mobile, String status,
            String discountCode, double discount, double total, double tax, double shipping, double grandtotal,
            String line1, String line2, String city, String province, int countryId, String country, String createdAt,
            String updatedAt, String content, String payment) {
        this.orderId = orderId;
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.status = status;
        this.discountCode = discountCode;
        this.discount = discount;
        this.total = total;
        this.tax = tax;
        this.shipping = shipping;
        this.grandtotal = grandtotal;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.province = province;
        this.countryId = countryId;
        this.country = country;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
        this.payment = payment;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(double grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    //khai bao cac thanh phan xu li db
    Connection cnn;//ket noi
    Statement stm;//thuc thi cac cau lenh
    PreparedStatement pstm;
    ResultSet rs, rs1;//luu tru va xu li du lieu

    protected void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn == null) {
                System.out.println("Connect Order failed!");
            }
        } catch (Exception e) {
        }
    }

    /*
    purpose: return a list of countries
    author: hieuddhe171241
    date: 24/05/2023
     */
    public List getListCountry() {
        List<Order> olist = new ArrayList<>();
        try {
            connect();
            String select = "SELECT [Country].id,[Country].name FROM [Country]";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt(1);
                String country = rs.getString(2);
                olist.add(new Order(countryId, country));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListCountry: " + e.getMessage());
        }
        return olist;
    }

    /*
    purpose: add tax and shipping to grand total of an order
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void updateTotal(int orderId, int countryId) {
        try {
            connect();
            double subTotal = 0, tax = 0, shipping = 0;
            String select = "SELECT [Order].subTotal FROM [Order] WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                subTotal = rs.getDouble(1);
            }
            select = "SELECT [Country].tax,[Country].shipping FROM [Country] WHERE [Country].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, countryId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                tax = rs.getDouble(1);
                shipping = rs.getDouble(2);
            }
            String update = "UPDATE [Order] SET [Order].grandTotal = ?, [Order].countryId = ? WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setDouble(1, subTotal + subTotal * (tax / 100) + subTotal * (shipping / 100));
            pstm.setInt(2, countryId);
            pstm.setInt(3, orderId);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("updateTotal: " + e.getMessage());
        }
    }

    /*
    purpose: update an order with an recently used address
    author: hieuddhe171241
    date: 09/06/2023
     */
    public void updateRecent(int orderId, String recentAddress) {
        try {
            connect();
            double subTotal = 0, tax = 0, shipping = 0;
            String[] sub = recentAddress.split(";");
            String[] subName = sub[0].split("-");
            this.countryId = Integer.parseInt(sub[6]);
            this.firstname = subName[0];
            this.lastname = subName[1];
            this.mobile = sub[1];
            this.line1 = sub[2];
            this.line2 = sub[3];
            this.city = sub[4];
            this.province = sub[5];
            String select = "SELECT [Order].subTotal FROM [Order] WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                subTotal = rs.getDouble(1);
            }
            select = "SELECT [Country].tax,[Country].shipping FROM [Country] WHERE [Country].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, this.countryId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                tax = rs.getDouble(1);
                shipping = rs.getDouble(2);
            }
            String update = "UPDATE [Order] SET [Order].firstName = ?, [Order].lastName = ?, [Order].mobile = ?, "
                    + "[Order].grandTotal = ?, [Order].line1 = ? , [Order].line2 = ?, [Order].city = ?, [Order].province = ?, "
                    + "[Order].countryId = ? WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setString(1, this.firstname);
            pstm.setString(2, this.lastname);
            pstm.setString(3, this.mobile);
            pstm.setDouble(4, subTotal + subTotal * (tax / 100) + subTotal * (shipping / 100));
            pstm.setString(5, this.line1);
            pstm.setString(6, this.line2);
            pstm.setString(7, this.city);
            pstm.setString(8, this.province);
            pstm.setInt(9, this.countryId);
            pstm.setInt(10, orderId);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("updateRecent: " + e.getMessage());
        }
    }

    /*
    purpose: apply discount to sub total of an order
    author: hieuddhe171241
    date: 24/05/2023
     */
    public boolean applyDiscount(int orderId, String discountCode) {
        try {
            connect();
            String select = "SELECT [Discount].amount FROM [Discount] WHERE [Discount].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, discountCode);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.discount = rs.getDouble(1);
                select = "SELECT [Order].subTotal,[Order].grandTotal FROM [Order] WHERE [Order].id = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, orderId);
                rs1 = pstm.executeQuery();
                while (rs1.next()) {
                    this.total = rs1.getDouble(1);
                    this.grandtotal = rs1.getDouble(2);
                }
                String update = "UPDATE [Order] SET [Order].subTotal = ?, [Order].discountCode = ?, [Order].grandTotal = ? "
                        + "WHERE [Order].id = ?";
                pstm = cnn.prepareStatement(update);
                pstm.setDouble(1, this.total - this.total * (this.discount / 100));
                pstm.setString(2, discountCode);
                if (this.grandtotal != 0) {
                    pstm.setDouble(3, this.grandtotal - this.total * (this.discount / 100));
                } else {
                    pstm.setDouble(3, 0);
                }
                pstm.setDouble(4, orderId);
                pstm.execute();
                cnn.close();
                return true;
            }
        } catch (SQLException e) {
            System.err.println("applyDiscount: " + e.getMessage());
        }
        return false;
    }

    /*
    purpose: return neccessary data the 'Cart' order to display in Cart page
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void getCart(int userId) {
        try {
            connect();
            String select = "SELECT [Order].id "
                    + "FROM [Order] WHERE [Order].userId = ? AND [Order].statusId = 0";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.orderId = rs.getInt(1);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getCart: " + e.getMessage());
        }
    }

    /*
    purpose: return data in detail of an order
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void getOrderDetail(int orderId) {
        try {
            connect();
            String select = "SELECT [Order].id, [Order].userId,[Order].firstName,[Order].lastName,[Order].mobile,"
                    + "[Order].statusId,[Order].discountCode,[Order].subTotal,[Order].grandTotal,[Order].line1,[Order].line2,"
                    + "[Order].city,[Order].province,[Order].countryId,CONVERT(varchar,[Order].createdAt,113),"
                    + "CONVERT(varchar,[Order].updatedAt,113),[Order].content,[Order].payment "
                    + "FROM [Order] WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.orderId = rs.getInt(1);
                this.userId = rs.getInt(2);
                this.firstname = rs.getString(3);
                this.lastname = rs.getString(4);
                this.mobile = rs.getString(5);
                this.statusId = rs.getInt(6);
                this.discountCode = rs.getString(7);
                this.total = rs.getDouble(8);
                this.grandtotal = rs.getDouble(9);
                this.line1 = rs.getString(10);
                this.line2 = rs.getString(11);
                this.city = rs.getString(12);
                this.province = rs.getString(13);
                this.countryId = rs.getInt(14);
                this.createdAt = rs.getString(15);
                this.updatedAt = rs.getString(16);
                this.content = rs.getString(17);
                this.payment = rs.getString(18);
            }
            select = "SELECT [Status].name FROM [Status] WHERE [Status].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, this.statusId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.status = rs.getString(1);
            }
            select = "SELECT [Country].name,[Country].tax,[Country].shipping FROM [Country] WHERE id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, this.countryId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.country = rs.getString(1);
                this.tax = this.total * (rs.getDouble(2) / 100);
                this.shipping = this.total * (rs.getDouble(3) / 100);
            }
            select = "SELECT [Discount].amount FROM [Discount] WHERE [Discount].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, this.discountCode);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.discount = rs.getDouble(1);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getOrderDetail: " + e.getMessage());
        }
    }

    /*
    purpose: return a list of orders 
    author: hieuddhe171241
    date: 24/05/2023
     */
    public List<Order> getListOrder(int userId) {
        List<Order> list = new ArrayList<>();
        try {
            connect();
            String select = "SELECT [Order].id FROM [Order] WHERE [Order].userId = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.getOrderDetail(rs.getInt(1));
                if (o.getStatusId() != 0 && o.getStatusId() != 1) {
                    list.add(o);
                }
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListOrder: " + e.getMessage());
        }
        return list;
    }

    /*
    purpose: checkout an order
    author: hieuddhe171241
    date: 24/05/2023
     */
    public boolean checkout(int userId, String[] orderItemId) {
        try {
            connect();
            int tmpId = 0;
            double subTotal = 0;
            User user = new User();
            user = user.getUserById(userId);
            Order order = new Order();
            order.getCart(userId);
            List<OrderItem> oilist = new ArrayList<>();
            String select = "SELECT [Order].id FROM [Order] WHERE [Order].statusId = 1 AND [Order].userId = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                tmpId = rs.getInt(1);
                String delete = "DELETE FROM [OrderItem] WHERE [OrderItem].orderId = ?";
                pstm = cnn.prepareStatement(delete);
                pstm.setInt(1, tmpId);
                pstm.execute();
                delete = "DELETE FROM [Order] WHERE [Order].id = ?";
                pstm = cnn.prepareStatement(delete);
                pstm.setInt(1, tmpId);
                pstm.execute();
            }
            for (String i : orderItemId) {
                OrderItem tmp = new OrderItem();
                tmp.getOrderItem(Integer.parseInt(i));
                select = "SELECT COUNT(productId) FROM [ProductInStock] "
                        + "WHERE [ProductInStock].productId = ? AND [ProductInStock].warrantyExp IS NULL";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, tmp.getProId());
                rs = pstm.executeQuery();
                if (rs.next()) {
                    if (tmp.getQuantity() > rs.getInt(1))
                        return false;
                }
                oilist.add(tmp);
            }
            String insert = "INSERT INTO [Order] (userId,firstName,lastName,mobile,statusId,subTotal) "
                    + "VALUES(?,?,?,?,?,?)";
            pstm = cnn.prepareStatement(insert);
            pstm.setInt(1, userId);
            pstm.setString(2, user.getFirstName());
            pstm.setString(3, user.getLastName());
            pstm.setString(4, user.getMobile());
            pstm.setInt(5, 1);
            for (OrderItem orderItem : oilist) {
                subTotal += orderItem.getOrderItemTotal();
            }
            pstm.setDouble(6, subTotal);
            pstm.execute();
            select = "SELECT [Order].id FROM [Order] WHERE [Order].statusId = 1 AND [Order].userId = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                tmpId = rs.getInt(1);
            }
            for (String i : orderItemId) {
                String update = "UPDATE [OrderItem] SET [OrderItem].orderId = ? WHERE [OrderItem].id = ?";
                pstm = cnn.prepareStatement(update);
                pstm.setInt(1, tmpId);
                pstm.setInt(2, Integer.parseInt(i));
                pstm.execute();
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("checkout: " + e.getMessage());
        }
        return true;
    }

    /*
    purpose: return data in details of 'Checkout' order
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void getCheckoutDetail(int userId) {
        try {
            connect();
            String select = "SELECT [Order].id,[Order].firstName,[Order].lastName,[Order].mobile,"
                    + "[Order].discountCode,[Order].subTotal,[Order].grandTotal,[Order].line1,[Order].line2,"
                    + "[Order].city,[Order].province,[Order].countryId,[Order].content "
                    + "FROM [Order] WHERE [Order].userId = ? AND [Order].statusId = 1";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.orderId = rs.getInt(1);
                this.firstname = rs.getString(2);
                this.lastname = rs.getString(3);
                this.mobile = rs.getString(4);
                this.discountCode = rs.getString(5);
                this.total = rs.getDouble(6);
                this.grandtotal = rs.getDouble(7);
                this.line1 = rs.getString(8);
                this.line2 = rs.getString(9);
                this.city = rs.getString(10);
                this.province = rs.getString(11);
                this.countryId = rs.getInt(12);
                this.content = rs.getString(13);
            }
            select = "SELECT [Country].name,[Country].tax,[Country].shipping "
                    + "FROM [Country] WHERE id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, this.countryId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.country = rs.getString(1);
                this.tax = this.total * (rs.getDouble(2) / 100);
                this.shipping = this.total * (rs.getDouble(3) / 100);
            }
            select = "SELECT [Discount].amount FROM [Discount] WHERE [Discount].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, this.discountCode);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.discount = rs.getDouble(1);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getCheckoutDetail: " + e.getMessage());
        }
    }

    /*
    purpose: return a list of old addresses
    author: hieuddhe171241
    date: 24/05/2023
     */
    public List<Order> getAddressList(int userId) {
        List<Order> list = new ArrayList<>();
        try {
            connect();
            String select = "SELECT DISTINCT [Order].firstName,[Order].lastName,[Order].mobile,"
                    + "[Order].line1,[Order].line2,[Order].city,[Order].province,[Order].countryId "
                    + "FROM [Order] WHERE [Order].userId = ? AND [Order].statusId > 1";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String firstname = rs.getString(1);
                String lastname = rs.getString(2);
                String mobile = rs.getString(3);
                String line1 = rs.getString(4);
                String line2 = rs.getString(5);
                String city = rs.getString(6);
                String province = rs.getString(7);
                int countryId = rs.getInt(8);
                String country = null;
                select = "SELECT [Country].name FROM [Country] WHERE id = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, countryId);
                rs1 = pstm.executeQuery();
                if (rs1.next()) {
                    country = rs1.getString(1);
                }
                list.add(new Order(firstname, lastname, mobile, line1, line2, city, province, countryId, country));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getAddressList: " + e.getMessage());
        }
        return list;
    }

    /*
    purpose: place an order in checkout stage
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void placeOrder(int orderId, String firstname, String lastname, String mobile,
            String line1, String line2, String city, String province, String content, String payment) {
        try {
            connect();
            String update = "UPDATE [Order] SET firstName = ?, lastName = ?, mobile = ?, statusId = 4, "
                    + "line1 = ?, line2= ?, city = ?, province = ?, createdAt = GETDATE(), content = ?, payment = ? "
                    + "WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setString(1, firstname);
            pstm.setString(2, lastname);
            pstm.setString(3, mobile);
            pstm.setString(4, line1);
            pstm.setString(5, line2);
            pstm.setString(6, city);
            pstm.setString(7, province);
            pstm.setString(8, content);
            pstm.setString(9, payment);
            pstm.setInt(10, orderId);
            pstm.execute();
            String select = "SELECT [OrderItem].id,[OrderItem].productId,[OrderItem].quantity FROM [OrderItem] "
                    + "WHERE [OrderItem].orderId = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int tmpId = rs.getInt(1);
                int tmpProId = rs.getInt(2);
                int tmpQty = rs.getInt(3);
                update = "UPDATE TOP (?) [ProductInStock] SET [ProductInStock].orderItemId = ? "
                        + "WHERE [ProductInStock].productId = ? AND [ProductInStock].warrantyExp IS NULL";
                pstm = cnn.prepareStatement(update);
                pstm.setInt(1, tmpQty);
                pstm.setInt(2, tmpId);
                pstm.setInt(3, tmpProId);
                pstm.execute();
                select = "SELECT [Product].warranty,[Product].sold "
                        + "FROM [Product] WHERE [Product].id = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, tmpProId);
                rs1 = pstm.executeQuery();
                while (rs1.next()) {
                    tmpQty += rs1.getInt(2);
                    update = "UPDATE [Product] SET [Product].sold = ? WHERE [Product].id = ?";
                    pstm = cnn.prepareStatement(update);
                    pstm.setInt(1, tmpQty);
                    pstm.setInt(2, tmpProId);
                    pstm.execute();
                    update = "UPDATE [ProductInStock] SET [ProductInStock].warrantyExp = DATEADD(year, ?, GETDATE()) "
                            + "WHERE [ProductInStock].orderItemId = ?";
                    pstm = cnn.prepareStatement(update);
                    pstm.setInt(1, rs1.getInt(1));
                    pstm.setInt(2, tmpId);
                    pstm.execute();
                }
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("placeOrder: " + e.getMessage());
        }
    }

    /*
    purpose: drop an order in checkout stage
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void dropOrder(int userId) {
        try {
            connect();
            int checkoutId = 0, cartId = 0;
            String select = "SELECT [Order].id FROM [Order] WHERE [Order].userId = ? AND [Order].statusId = 0";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                cartId = rs.getInt(1);
            }
            select = "SELECT [Order].id FROM [Order] WHERE [Order].userId = ? AND [Order].statusId = 1";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                checkoutId = rs.getInt(1);
            }
            String update = "UPDATE [OrderItem] SET [OrderItem].orderId = ? WHERE [OrderItem].orderId = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setInt(1, cartId);
            pstm.setInt(2, checkoutId);
            pstm.execute();
            String delete = "DELETE [Order] WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(delete);
            pstm.setInt(1, checkoutId);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("dropOrder: " + e.getMessage());
        }
    }

    /*
    purpose: cancel an order after check out
    author: hieuddhe171241
    date: 19/06/2023
     */
    public void cancelOrder(int orderId) {
        try {
            connect();
            String select = "SELECT [OrderItem].id,[OrderItem].productId,[OrderItem].quantity FROM [OrderItem] WHERE [OrderItem].orderId = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                select = "SELECT [Product].sold FROM [Product] WHERE [Product].id = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, rs.getInt(2));
                rs1 = pstm.executeQuery();
                if (rs1.next()) {
                    String update = "UPDATE [Product] SET [Product].sold = ? WHERE [Product].id = ?";
                    pstm = cnn.prepareStatement(update);
                    pstm.setInt(1, rs1.getInt(1) - rs.getInt(3));
                    pstm.setInt(2, rs.getInt(2));
                    pstm.execute();
                }
                String update = "UPDATE [ProductInStock] SET orderItemId = NULL, warrantyExp = NULL WHERE [ProductInStock].orderItemId = ?";
                pstm = cnn.prepareStatement(update);
                pstm.setInt(1, rs.getInt(1));
                pstm.execute();
                update = "UPDATE [OrderItem] SET [OrderItem].quantity = 0 WHERE [OrderItem].id = ?";
                pstm = cnn.prepareStatement(update);
                pstm.setInt(1, rs.getInt(1));
                pstm.execute();
            }
            String update = "UPDATE [Order] SET [Order].statusId = 5 WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setInt(1, orderId);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("cancelOrder: " + e.getMessage());
        }
    }
}
