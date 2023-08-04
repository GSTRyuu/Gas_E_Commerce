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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Product;

/**
 *
 * @author Dell
 */
public class ServiceItem {

    private int id;
    private String code, serial, firstname, lastname, mobile;
    private double total;
    private String line1, line2, city, province;
    private int countryId;
    private String country, createdAt, content;
    private int statusId;
    private String status, startAt, endAt;

    public ServiceItem() {
    }

    public ServiceItem(int id, String code, String serial, String firstname, String lastname,
            String mobile, double total, String line1, String line2, String city, String province,
            int countryId, String country, String createdAt, String content, int statusId,
            String status, String startAt, String endAt) {
        this.id = id;
        this.code = code;
        this.serial = serial;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.total = total;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.province = province;
        this.countryId = countryId;
        this.country = country;
        this.createdAt = createdAt;
        this.content = content;
        this.statusId = statusId;
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    purpose: return detail of a service item
    author: hieuddhe171241
    date: 29/06/2023
     */
    public void getServiceItemDetail(int id) {
        try {
            connect();
            String select = "SELECT [product_service].id,[product_service].code,[product_service].serial,"
                    + "[product_service].firstName,[product_service].lastName,[product_service].mobile,"
                    + "[product_service].total,[product_service].line1,[product_service].line2,[product_service].city,"
                    + "[product_service].province,[product_service].countryId,"
                    + "CONVERT(varchar,[product_service].createdAt,29),[product_service].content,"
                    + "[product_service].statusId,[product_service].startAt,[product_service].endAt "
                    + "FROM [product_service] WHERE [product_service].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt(1);
                this.code = rs.getString(2);
                this.serial = rs.getString(3);
                this.firstname = rs.getString(4);
                this.lastname = rs.getString(5);
                this.mobile = rs.getString(6);
                this.total = rs.getDouble(7);
                this.line1 = rs.getString(8);
                this.line2 = rs.getString(9);
                this.city = rs.getString(10);
                this.province = rs.getString(11);
                this.countryId = rs.getInt(12);
                this.createdAt = rs.getString(13);
                this.content = rs.getString(15);
                this.statusId = rs.getInt(16);
                this.startAt = rs.getString(17);
                this.endAt = rs.getString(18);
            }
            select = "SELECT [Country].name FROM [Country] WHERE [Country].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, this.countryId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.country = rs.getString(1);
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
            System.err.println("getServiceItemDetail: " + e.getMessage());
        }
    }

    /*
    purpose: request a service item
    author: hieuddhe171241
    date: 29/06/2023
     */
    public void requestSevice(String code, String serial, String firstname, String lastname, String mobile,
            double total, String line1, String line2, String city, String province, int countryId, String content) {
        try {
            connect();
            if (!code.contains("EXT")) {
                String select = "SELECT [ProductInStock].warrantyExp FROM [ProductInStock] "
                        + "WHERE [ProductInStock].serial = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, serial);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String tmp = dtf.format(now);
                    int cmp = tmp.compareToIgnoreCase(rs.getString(1));
                    if (cmp < 0) {
                        total = 0;
                    } 
                }
            }
            String insert = "INSERT INTO [product_service]([product_service].code,[product_service].serial,"
                    + "[product_service].firstName,[product_service].lastName,[product_service].mobile,"
                    + "[product_service].total,[product_service].line1,[product_service].line2,[product_service].city,"
                    + "[product_service].province,[product_service].countryId,[product_service].createdAt,"
                    + "[product_service].content,[product_service].statusId) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,GETDATE(),?,20)";
            pstm = cnn.prepareStatement(insert);
            pstm.setString(1, code);
            pstm.setString(2, serial);
            pstm.setString(3, firstname);
            pstm.setString(4, lastname);
            pstm.setString(5, mobile);
            pstm.setDouble(6, total);
            pstm.setString(7, line1);
            pstm.setString(8, line2);
            pstm.setString(9, city);
            pstm.setString(10, province);
            pstm.setInt(11, countryId);
            pstm.setString(12, content);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("requestSevice: " + e.getMessage());
        }
    }

    /*
    purpose: return a service item by ID
    author: hieuddhe171241
    date: 29/06/2023
     */
    public ServiceItem searchServiceItem(int id) {
        ServiceItem si = null;
        try {
            connect();
            String select = "SELECT [product_service].id "
                    + "FROM [product_service] WHERE [product_service].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                si.getServiceItemDetail(rs.getInt(1));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("searchServiceItem: " + e.getMessage());
        }
        return si;
    }

    /*
    purpose: return data of a product bt serial number
    author: hieuddhe171241
    date: 29/06/2023
     */
    public Product getProductInfo(String serial) {
        Product p = new Product();
        try {
            connect();
            String select = "SELECT [ProductInStock].productId "
                    + "FROM [ProductInStock] WHERE [ProductInStock].serial = ? "
                    + "AND [ProductInStock].warrantyExp IS NOT NULL";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, serial);
            rs = pstm.executeQuery();
            if (rs.next()) {
                p = p.getProductByID(rs.getInt(1));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getProductInfo: " + e.getMessage());
        }
        return p;
    }
}
