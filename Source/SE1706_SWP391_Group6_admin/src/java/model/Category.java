/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Category {
    int categoryid;
    String cname;
    String description;

    public Category() {
    }

    public Category(int categoryid, String cname, String description) {
        this.categoryid = categoryid;
        this.cname = cname;
        this.description = description;
    }
    
    Connection cnn; //dùng để kết nối
    Statement stm; //Thực thi các câu lệnh sql
    PreparedStatement pstm;
    ResultSet rs; //Lưu trữ và xử lý dữ liệu
    
    private void connect() {
        try {
            cnn = (new DBContext()).connection;
            if (cnn != null) {
                System.out.println("Connect category success");
            } else {
                System.out.println("Connect fail");
            }
        } catch (Exception e) {

        }
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Category> getListCategory() {
        List<Category> data = new ArrayList<Category>();
        try {
            connect();
            String strSelect = "select [id], [name], [description] from Category";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {  
                int categoryid = rs.getInt(1);
                String cname = rs.getString(2);
                String description = rs.getString(3);
                data.add(new Category(categoryid, cname, description));
            }          
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListCategory: " + e.getMessage());
        }
        return data;
    }
    
    public Category getCategoryByID(int cid) {
        try {
            connect();
            String strSelect = "select [id], [name], [description] from Category where id=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, cid);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int categoryid = rs.getInt(1);
                String cname = rs.getString(2);
                String description = rs.getString(3);
                return new Category(categoryid, cname, description);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListProduct: " + e.getMessage());
        }
        return null;
    }
}
