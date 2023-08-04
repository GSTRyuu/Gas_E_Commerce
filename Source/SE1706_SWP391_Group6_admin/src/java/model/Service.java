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
public class Service {

    private String code, type, name;
    private int amount;
    private double price;
    private String status, content, createAt, updateAt;

    public Service() {
    }

    public Service(String code, String type, String name, double price, String status) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public Service(String code, String type, String name, int amount, double price, String status, String content, String createAt, String updateAt) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.status = status;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
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
                System.out.println("Connect Service failed!");
            }
        } catch (Exception e) {
        }
    }

    /*
    purpose: return general detail of a service
    author: hieuddhe171241
    date: 26/06/2023
     */
    public void getGeneralDetail(String code) {
        try {
            connect();
            String select = "SELECT [Service].code,[Service].type,[Service].name,"
                    + "[Service].price,[Service].status "
                    + "FROM [Service] WHERE [Service].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.code = rs.getString(1);
                this.type = rs.getString(2);
                this.name = rs.getString(3);
                this.price = rs.getDouble(4);
                this.status = rs.getString(5);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getGeneralDetail: " + e.getMessage());
        }
    }

    /*
    purpose: return detail of a service
    author: hieuddhe171241
    date: 26/06/2023
     */
    public void getServiceDetail(String code) {
        try {
            connect();
            String select = "SELECT [Service].code,[Service].type,[Service].name,"
                    + "[Service].amount,[Service].price,[Service].status,[Service].content,"
                    + "CONVERT(varchar,[Service].createdAt,29),CONVERT(varchar,[Service].updatedAt,29) "
                    + "FROM [Service] WHERE [Service].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            while (rs.next()) {
                this.code = rs.getString(1);
                this.type = rs.getString(2);
                this.name = rs.getString(3);
                this.amount = rs.getInt(4);
                this.price = rs.getDouble(5);
                this.status = rs.getString(6);
                this.content = rs.getString(7);
                this.createAt = rs.getString(8);
                this.updateAt = rs.getString(9);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getServiceDetail: " + e.getMessage());
        }
    }

    /*
    purpose: return list of all services
    author: hieuddhe171241
    date: 26/06/2023
     */
    public List getListServices(int index, String filterBy, String ofType) {
        List<Service> sList = new ArrayList<>();
        try {
            connect();
            String select;
            if (ofType.equals("all") && filterBy.equals("all")) {
                select = "SELECT [Service].code FROM [Service] WHERE [Service].type IS NOT NULL "
                        + "ORDER BY [Service].price DESC OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, (index - 1) * 10);
            } else if (ofType.equals("all") && !filterBy.equals("all")) {
                select = "SELECT [Service].code FROM [Service] WHERE [Service].type IS NOT NULL AND [Service].status = ? "
                        + "ORDER BY [Service].price DESC OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, filterBy);
                pstm.setInt(2, (index - 1) * 10);
            } else if (filterBy.equals("all") && !ofType.equals("all")) {
                select = "SELECT [Service].code FROM [Service] WHERE [Service].type IS NOT NULL AND [Service].type = ? "
                        + "ORDER BY [Service].price DESC OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, ofType);
                pstm.setInt(2, (index - 1) * 10);
            } else {
                select = "SELECT [Service].code FROM [Service] WHERE [Service].type IS NOT NULL AND [Service].type = ? AND [Service].status = ? "
                        + "ORDER BY [Service].price DESC OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, ofType);
                pstm.setString(2, filterBy);
                pstm.setInt(3, (index - 1) * 10);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.getGeneralDetail(rs.getString(1));
                sList.add(s);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListServices: " + e.getMessage());
        }
        return sList;
    }

    /*
    purpose: add a new service
    author: hieuddhe171241
    date: 26/06/2023
     */
    public boolean addService(String code, String type, String name, int amount,
            double price, String status, String content) {
        try {
            connect();
            String select = "SELECT [Service].code FROM [Service] "
                    + "WHERE [Service].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return false;
            }
            String insert = "INSERT INTO [Service](code,type,name,amount,price,status,content,createdAt) "
                    + "VALUES(?,?,?,?,?,?,?,GETDATE())";
            pstm = cnn.prepareStatement(insert);
            pstm.setString(1, code);
            pstm.setString(2, type);
            pstm.setString(3, name);
            pstm.setInt(4, amount);
            pstm.setDouble(5, price);
            pstm.setString(6, status);
            pstm.setString(7, content);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("addService: " + e.getMessage());
        }
        return true;
    }

    /*
    purpose: update an existing service
    author: hieuddhe171241
    date: 26/062023
     */
    public boolean updateService(String oldCode, String code, String type, String name, int amount,
            double price, String status, String content) {
        try {
            connect();
            if (!oldCode.equals(code)) {
                String select = "SELECT [Service].code FROM [Service] "
                        + "WHERE [Service].code = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, code);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return false;
                }
            }
            String update = "UPDATE [Service] SET　code = ?, type = ?, name = ?, "
                    + "amount = ?, price = ?, status = ?, content = ?, updatedAt = GETDATE() "
                    + "WHERE [Service].code = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setString(1, code);
            pstm.setString(2, type);
            pstm.setString(3, name);
            pstm.setInt(4, amount);
            pstm.setDouble(5, price);
            pstm.setString(6, status);
            pstm.setString(7, content);
            pstm.setString(8, oldCode);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("updateService: " + e.getMessage());
        }
        return true;
    }

    /*
    purpose: delete an existing service
    author: hieuddhe171241
    date: 26/06/2023
     */
    public boolean deleteService(String code) {
        try {
            connect();
            String select = "SELECT [product_service].code FROM [product_service] "
                    + "WHERE [product_service].code = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                String delete = "DELETE FROM [Service] WHERE [Service].code = ?";
                pstm = cnn.prepareStatement(delete);
                pstm.setString(1, code);
                pstm.execute();
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("deleteService: " + e.getMessage());
        }
        return true;
    }

    /*
    purpose: search for services
    author: hieuddhe171241
    date: 26/06/2023
     */
    public List searchServices(String input) {
        List<Service> sList = new ArrayList<>();
        try {
            connect();
            String select = "SELECT [Service].code FROM [Service] "
                    + "WHERE [Service].type IS NOT NULL AND [Service].code like '%" + input + "%' OR "
                    + "[Service].name like '%" + input + "%'";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.getGeneralDetail(rs.getString(1));
                sList.add(s);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("searchServices: " + e.getMessage());
        }
        return sList;
    }

    /*
    purpose: update status of an existing service
    author: hieuddhe171241
    date: 26/06/2023
     */
    public void updateStatus(String code, String status) {
        try {
            connect();
            String update = "UPDATE [Service] SET [Service].status = ? "
                    + "WHERE [Service].code = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setString(1, status);
            pstm.setString(2, code);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("updateStatus: " + e.getMessage());
        }
    }

    /*
    purpose: get list types of services
    author: hieuddhe171241
    date: 26/06/2023
     */
    public List getListType() {
        List<String> tList = new ArrayList<>();
        try {
            connect();
            String select = "SELECT DISTINCT [Service].type FROM [Service] "
                    + "WHERE [Service].type IS NOT NULL";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                tList.add(rs.getString(1));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListType: " + e.getMessage());
        }
        return tList;
    }

    /*
    purpose: get list status of services
    author: hieuddhe171241
    date: 26/06/2023
     */
    public List getListStatus() {
        List<String> stList = new ArrayList<>();
        try {
            connect();
            String select = "SELECT DISTINCT [Service].status FROM [Service] "
                    + "WHERE [Service].type IS NULL";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                stList.add(rs.getString(1));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStatus: " + e.getMessage());
        }
        return stList;
    }

    /*
    purpose: get end page of when dividing list service into pages
    author: hieuddhe171241
    date: 26/06/2023
     */
    public int getEndPage(String ofType, String filterBy) {
        try {
            connect();
            String select;
            if (ofType.equals("all") && filterBy.equals("all")) {
                select = "SELECT COUNT(*) FROM [Service]";
                pstm = cnn.prepareStatement(select);
            } else if (!ofType.equals("all") && filterBy.equals("all")) {
                select = "SELECT COUNT(*) FROM [Service] WHERE [Service].type = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, ofType);
            } else if (ofType.equals("all") && !filterBy.equals("all")) {
                select = "SELECT COUNT(*) FROM [Service] WHERE [Service].status = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, filterBy);
            } else if (!ofType.equals("all") && !filterBy.equals("all")) {
                select = "SELECT COUNT(*) FROM [Service] WHERE [Service].type = ? AND [Service].status = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, ofType);
                pstm.setString(2, filterBy);
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
