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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class Order {

    private int orderId, userId;
    private String userMobile, userFirstname, userLastname, email, firstname, lastname, mobile;
    private int statusId;
    private String status;
    private String discountCode;
    private double discount, total, tax, shipping, grandtotal;
    private String line1, line2, city, province;
    private int countryId;
    private String country, createdAt, updatedAt, content, payment;

    public Order() {
    }

    public Order(int orderId, int userId, String userMobile, String userFirstname, String userLastname, String email,
            String firstname, String lastname, String mobile, String status, String discountCode, double discount,
            double total, double tax, double shipping, double grandtotal, String line1, String line2, String city,
            String province, int countryId, String country, String createdAt, String updatedAt, String content, String payment) {
        this.orderId = orderId;
        this.userId = userId;
        this.userMobile = userMobile;
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.email = email;
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

    public String getUserMobile() {
        return userMobile;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    purpose: return data in detail of an order
    author: hieuddhe171241
    date: 28/05/2023
     */
    public void getOrderDetail(int orderId) {
        try {
            connect();
            String select = "SELECT [Order].id, [Order].userId,[Order].firstName,[Order].lastName,[Order].mobile,"
                    + "[Order].statusId,[Order].discountCode,[Order].subTotal,[Order].grandTotal,[Order].line1,[Order].line2,"
                    + "[Order].city,[Order].province,[Order].countryId,CONVERT(varchar,[Order].createdAt,29),"
                    + "CONVERT(varchar,[Order].updatedAt,29),[Order].content,[Order].payment "
                    + "FROM [Order] WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.orderId = rs.getInt(1);
                this.userId = rs.getInt(2);
                select = "SELECT [User].firstName,[User].lastName,[User].email FROM [User] WHERE [User].id = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, this.userId);
                rs1 = pstm.executeQuery();
                while (rs1.next()) {
                    this.userFirstname = rs1.getString(1);
                    this.userLastname = rs1.getString(2);
                    this.email = rs1.getString(3);
                }
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
            select = "SELECT [User].mobile FROM [User] WHERE id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, this.userId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.userMobile = rs.getString(1);
            }
            select = "SELECT [Country].name,[Country].tax,[Country].shipping FROM [Country] WHERE id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, this.countryId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.country = rs.getString(1);
                this.tax = this.total * (rs.getDouble(2) / 100);
                this.shipping = this.total * (rs.getDouble(3) / 100);
            }
            select = "SELECT [Discount].amount FROM [Discount] WHERE [Discount].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, this.discountCode);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.discount = rs.getDouble(1);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getOrderDetail: " + e.getMessage());
        }
    }

    /*
    purpose: return general data of an order
    author: hieuddhe171241
    date: 28/05/2023
     */
    public void getOrderGeneralDetail(int orderId) {
        try {
            connect();
            String select = "SELECT [Order].id,[Order].statusId,[Order].grandTotal,"
                    + "CONVERT(varchar,[Order].createdAt,29) "
                    + "FROM [Order] WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, orderId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.orderId = rs.getInt(1);
                this.statusId = rs.getInt(2);
                this.grandtotal = rs.getDouble(3);
                this.createdAt = rs.getString(4);
            }
            select = "SELECT [Status].name FROM [Status] WHERE [Status].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, this.statusId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.status = rs.getString(1);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getOrderGeneralDetail: " + e.getMessage());
        }
    }

    /*
    purpose: return a list of orders 
    author: hieuddhe171241
    date: 28/05/2023
     */
    public List getListOrder(int index, String sortBy, String filterBy) {
        List<Order> list = new ArrayList<>();
        try {
            connect();
            String select;
            if (sortBy.equals("default") && filterBy.equals("all")) {
                select = "SELECT [Order].id FROM [Order] ORDER BY [Order].id OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, (index - 1) * 10);
            } else if (sortBy.equals("default") && !filterBy.equals("all")) {
                select = "SELECT [Order].id FROM [Order] WHERE [Order].statusId = ? ORDER BY [Order].id OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, Integer.parseInt(filterBy));
                pstm.setInt(2, (index - 1) * 10);
            } else if (filterBy.equals("all") && !sortBy.equals("default")) {
                select = "SELECT [Order].id FROM [Order] ORDER BY " + sortBy + " OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, (index - 1) * 10);
            } else {
                select = "SELECT [Order].id FROM [Order] WHERE [Order].statusId = ? ORDER BY " + sortBy + " OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, Integer.parseInt(filterBy));
                pstm.setInt(2, (index - 1) * 10);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.getOrderGeneralDetail(rs.getInt(1));
                list.add(o);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListOrder: " + e.getMessage());
        }
        return list;
    }

    /*
    purpose: return a list of orders based on ID
    author: hieuddhe171241
    date: 14/06/2023
     */
    public List getListOrderById(String orderId) {
        List<Order> list = new ArrayList<>();
        try {
            connect();
            Order o = new Order();
            if (!orderId.isEmpty()) {
                o.getOrderGeneralDetail(Integer.parseInt(orderId));
                if (o.orderId != 0) {
                    list.add(o);
                }
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListOrderById: " + e.getMessage());
        }
        return list;
    }

    /*
    purpose: get list status of order
    author: hieuddhe171241
    date: 03/07/2023
     */
    public List getListStatus() {
        List<String[]> stList = new ArrayList<>();
        try {
            connect();
            String select = "SELECT [Status].id,[Status].name FROM [Status] "
                    + "WHERE [Status].id < 20";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String[] tmp = new String[2];
                tmp[0] = rs.getString(1);
                tmp[1] = rs.getString(2);
                stList.add(tmp);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStatus(): " + e.getMessage());
        }
        return stList;
    }

    /*
    purpose: return total money spent of all orders
    author: hieuddhe171241
    date: 24/05/2023
     */
    public double getTotalSpent() {
        double sum = 0;
        try {
            connect();
            String select = "SELECT [Order].grandTotal FROM [Order] WHERE [Order].statusId in (4, 6, 7)";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                sum += rs.getDouble(1);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getTotalSpent: " + e.getMessage());
        }
        return sum;
    }

    /*
    purpose: return total money spent of all orders by month
    author: hieuddhe171241
    date: 24/05/2023
     */
    public List getTotalSpentByMonth() {
        List<Double> listData = new ArrayList<>();
        try {
            connect();
            for (int i = 1; i < 13; i++) {
                double sum = 0;
                String select = "SELECT [Order].grandTotal FROM [Order] "
                        + "WHERE [Order].statusId in (4, 6, 7) "
                        + "AND MONTH(createdAt) = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, i);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    sum += rs.getDouble(1);
                }
                listData.add(sum);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getTotalSpentByMonth: " + e.getMessage());
        }
        return listData;
    }

    /*
    purpose: get end page of when dividing list orders into pages
    author: hieuddhe171241
    date: 24/05/2023
     */
    public int getEndPage(String sortBy, String filterBy) {
        try {
            connect();
            String select;
            if (sortBy.equals("default") && filterBy.equals("all")) {
                select = "SELECT [Order].id FROM [Order] ORDER BY [Order].id";
                pstm = cnn.prepareStatement(select);
            } else if (sortBy.equals("default") && !filterBy.equals("all")) {
                select = "SELECT [Order].id FROM [Order] WHERE [Order].statusId = ? ORDER BY [Order].id";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, Integer.parseInt(filterBy));
            } else if (filterBy.equals("all") && !sortBy.equals("default")) {
                select = "SELECT [Order].id FROM [Order] ORDER BY " + sortBy;
                pstm = cnn.prepareStatement(select);
            } else {
                select = "SELECT [Order].id FROM [Order] WHERE [Order].statusId = ? ORDER BY " + sortBy;
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, Integer.parseInt(filterBy));
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                int endPage = rs.getInt(1) / 10;
                if (rs.getInt(1) % 10 != 0) {
                    endPage++;
                }
                return endPage;
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("updateStatus: " + e.getMessage());
        }
        return 0;
    }

    /*
    purpose: modify status of an order
    author: hieuddhe171241
    date: 24/05/2023
     */
    public void updateStatus(int orderId, int statusId) {
        try {
            connect();
            String update = "UPDATE [Order] SET statusId = ? WHERE [Order].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setInt(1, statusId);
            pstm.setInt(2, orderId);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("updateStatus: " + e.getMessage());
        }
    }
}
