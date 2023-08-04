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
public class ServiceItem {

    private int id;
    private String code, type, description, serial, firstname, lastname, mobile;
    private double total;
    private String line1, line2, city, province;
    private int countryId;
    private String country, createdAt, content;
    private int statusId;
    private String status, startAt, endAt;

    public ServiceItem() {
    }

    public ServiceItem(int id, String code, String type, String description, String serial, String firstname, String lastname,
            String mobile, double total, String line1, String line2, String city, String province,
            int countryId, String country, String createdAt, String content, int statusId,
            String status, String startAt, String endAt) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
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
                System.out.println("Connect Service Item failed!");
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
                    + "[product_service].statusId,CONVERT(varchar,[product_service].startAt,29),"
                    + "CONVERT(varchar,[product_service].endAt,29) "
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
                this.content = rs.getString(14);
                this.statusId = rs.getInt(15);
                this.startAt = rs.getString(16);
                this.endAt = rs.getString(17);
            }
            select = "SELECT [Service].type,[Service].content FROM [Service] WHERE [Service].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, this.code);
            rs = pstm.executeQuery();
            if (rs.next()) {
                this.type = rs.getString(1);
                this.description = rs.getString(2);
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
    purpose: return list of service request
    author: hieuddhe171241
    date: 02/07/2023
     */
    public List getListRequest(int index, String ofStatus, String sortBy) {
        List<ServiceItem> siList = new ArrayList<>();
        try {
            connect();
            String select;
            if (ofStatus.equals("all") && sortBy.equals("all")) {
                select = "SELECT [product_service].id FROM [product_service] "
                        + "ORDER BY [product_service].createdAt DESC OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, (index - 1) * 10);
            } else if (!ofStatus.equals("all") && sortBy.equals("all")) {
                select = "SELECT [product_service].id FROM [product_service] "
                        + "WHERE [product_service].statusId = ? "
                        + "ORDER BY [product_service].createdAt DESC OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, Integer.parseInt(ofStatus));
                pstm.setInt(2, (index - 1) * 10);
            } else if (ofStatus.equals("all") && !sortBy.equals("all")) {
                select = "SELECT [product_service].id FROM [product_service] "
                        + "ORDER BY " + sortBy + " OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                System.out.println(select);
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, (index - 1) * 10);
            } else {
                select = "SELECT [product_service].id FROM [product_service] "
                        + "WHERE [product_service].statusId = ? "
                        + "ORDER BY " + sortBy + " OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, Integer.parseInt(ofStatus));
                pstm.setInt(2, (index - 1) * 10);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                ServiceItem si = new ServiceItem();
                si.getServiceItemDetail(rs.getInt(1));
                siList.add(si);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListRequest: " + e.getMessage());
        }
        return siList;
    }

    /*
    purpose: request a service item
    author: hieuddhe171241
    date: 29/06/2023
     */
    public List searchServiceItem(int id) {
        List<ServiceItem> siList = new ArrayList<>();
        try {
            connect();
            String select = "SELECT [product_service].id "
                    + "FROM [product_service] WHERE [product_service].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                ServiceItem si = new ServiceItem();
                si.getServiceItemDetail(rs.getInt(1));
                siList.add(si);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("searchServiceItem: " + e.getMessage());
        }
        return siList;
    }

    /*
    purpose: request a service item
    author: hieuddhe171241
    date: 29/06/2023
     */
    public Product getProductInfo(String serial) {
        Product p = new Product();
        try {
            connect();
            String select = "SELECT [ProductInStock].productId "
                    + "FROM [ProductInStock] WHERE [ProductInStock].serial = ?";
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

    /*
    purpose: update status of a request
    author: hieuddhe171241
    date: 02/07/2023
     */
    public void approveRequest(int id) {
        try {
            connect();
            String update = "UPDATE [product_service] SET [product_service].statusId = 21,"
                    + "[startAt] = GETDATE() "
                    + "WHERE [product_service].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setInt(1, id);
            pstm.execute();
            String code = null, serial = null;
            int amount = 0;
            String select = "SELECT [product_service].code, [product_service].serial "
                    + "FROM [product_service] WHERE [product_service].id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                code = rs.getString(1);
                serial = rs.getString(2);
            }
            if (code.contains("EXT")) {
                select = "SELECT [Service].amount "
                        + "FROM [Service] WHERE [Service].code = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, code);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    amount = rs.getInt(1);
                }
                update = "UPDATE [ProductInStock] SET [ProductInStock].warrantyExp = DATEADD(year, ?, warrantyExp) "
                        + "WHERE [ProductInStock].serial = ?";
                pstm = cnn.prepareStatement(update);
                pstm.setInt(1, amount);
                pstm.setString(2, serial);
                pstm.execute();
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("approveRequest: " + e.getMessage());
        }
    }

    /*
    purpose: update status of a request
    author: hieuddhe171241
    date: 02/07/2023
     */
    public void rejectRequest(int id) {
        try {
            connect();
            String update = "UPDATE [product_service] SET [product_service].statusId = 22 "
                    + "WHERE [product_service].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setInt(1, id);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("rejectRequest: " + e.getMessage());
        }
    }

    /*
    purpose: update status of a request
    author: hieuddhe171241
    date: 02/07/2023
     */
    public void completeRequest(int id) {
        try {
            connect();
            String update = "UPDATE [product_service] SET [product_service].statusId = 23,"
                    + "[endAt] = GETDATE() "
                    + "WHERE [product_service].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setInt(1, id);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("completeRequest: " + e.getMessage());
        }
    }

    /*
    purpose: get list status of request
    author: hieuddhe171241
    date: 02/07/2023
     */
    public List getListStatus() {
        List<String[]> stList = new ArrayList<>();
        try {
            connect();
            String select = "SELECT [Status].id,[Status].name FROM [Status] "
                    + "WHERE [Status].id > 19";
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
    purpose: get end page of when dividing list service items into pages
    author: hieuddhe171241
    date: 02/07/2023
     */
    public int getEndPage(String ofStatus, String sortBy) {
        try {
            connect();
            String select;
            if (ofStatus.equals("all") && sortBy.equals("all")) {
                select = "SELECT COUNT(*) FROM [product_service]";
                pstm = cnn.prepareStatement(select);
            } else if (!ofStatus.equals("all") && sortBy.equals("all")) {
                select = "SELECT COUNT(*) FROM [product_service] WHERE [product_service].statusId = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, ofStatus);
            } else if (ofStatus.equals("all") && !sortBy.equals("all")) {
                select = "SELECT COUNT(*) FROM [Service] WHERE [Service].status = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, sortBy);
            } else if (!ofStatus.equals("all") && !sortBy.equals("all")) {
                select = "SELECT COUNT(*) FROM [Service] WHERE [Service].type = ? AND [Service].status = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, ofStatus);
                pstm.setString(2, sortBy);
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
            System.err.println("getEndPages: " + e.getMessage());
        }
        return 0;
    }
}
