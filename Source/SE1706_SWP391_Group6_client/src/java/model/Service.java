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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class Service {

    private String warrantyExp, code, type, name;
    private int amount;
    private double price;
    private String content;

    public Service() {
    }

    public Service(String code, String type, String name, int amount, double price, String content) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWarrantyExp() {
        return warrantyExp;
    }

    public void setWarrantyExp(String warrantyExp) {
        this.warrantyExp = warrantyExp;
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
    purpose: return expiration date of warranty
    author: hieuddhe171241
    date: 25/06/2023
     */
    public void getExpDate(String serial) {
        try {
            connect();
            String select = "SELECT CONVERT(varchar,[ProductInStock].warrantyExp,25) FROM [ProductInStock] "
                    + "WHERE [ProductInStock].serial = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, serial);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.warrantyExp = rs.getString(1);
            }
            //cnn.close();
        } catch (SQLException e) {
            System.err.println("getExpDate: " + e.getMessage());
        }
    }

    /*
    purpose: return a list of types
    author: hieuddhe171241
    date: 25/06/2023
     */
    public List getListType() {
        List<String> types = new ArrayList<>();
        try {
            connect();
            String select = "SELECT DISTINCT [Service].type FROM [Service]　"
                    + "WHERE [Service].status = 'activated' AND [Service].type IS NOT NULL";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                types.add(rs.getString(1));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListType: " + e.getMessage());
        }
        return types;
    }

    /*
    purpose: return detail of a service
    author: hieuddhe171241
    date: 25/06/2023
     */
    public void getRequestDetail(String code, String serial) {
        try {
            this.getExpDate(serial);
            connect();
            String select = "SELECT [Service].code,[Service].type,[Service].name,"
                    + "[Service].amount,[Service].price,[Service].content "
                    + "FROM [Service] WHERE [Service].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.code = rs.getString(1);
                this.type = rs.getString(2);
                this.name = rs.getString(3);
                this.amount = rs.getInt(4);
                if (!this.code.contains("EXT")) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String tmp = dtf.format(now);
                    int cmp = tmp.compareToIgnoreCase(this.getWarrantyExp());
                    if (cmp < 0) {
                        this.price = 0;
                    } else {
                        this.price = rs.getDouble(5);
                    }
                } else {
                    this.price = rs.getDouble(5);
                }
                this.content = rs.getString(6);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getRequestDetail: " + e.getMessage());
        }
    }

    /*
    purpose: return detail of a service
    author: hieuddhe171241
    date: 25/06/2023
     */
    public void getServiceDetail(String code) {
        try {
            connect();
            String select = "SELECT [Service].code,[Service].type,[Service].name,"
                    + "[Service].amount,[Service].price,[Service].content "
                    + "FROM [Service] WHERE [Service].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.code = rs.getString(1);
                this.type = rs.getString(2);
                this.name = rs.getString(3);
                this.amount = rs.getInt(4);
                this.price = rs.getDouble(5);
                this.content = rs.getString(6);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getServiceDetail: " + e.getMessage());
        }
    }

    /*
    purpose: return a list of services
    author: hieuddhe171241
    date: 25/06/2023
     */
    public List getListService() {
        List<Service> sList = new ArrayList<>();
        try {
            connect();
            String select = "SELECT [Service].code FROM [Service] "
                    + "WHERE [Service].status = 'activated' AND [Service].type IS NOT NULL";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.getServiceDetail(rs.getString(1));
                sList.add(s);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListService: " + e.getMessage());
        }
        return sList;
    }

    public List<Service> getListServiceByType(String ofType) {
        List<Service> sList = new ArrayList<>();
        try {
            connect();
            String select = "SELECT [Service].code FROM [Service] "
                    + "WHERE [Service].status = 'activated' AND [Service].type = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, ofType);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.getServiceDetail(rs.getString(1));
                sList.add(s);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListServiceByType: " + e.getMessage());
        }
        return sList;
    }
}
