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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductComment {
    int pcid;
    int pid;
    int userid;
    String name;
    String email;
    String title;
    double rating;
    String image;
    String createdAt;
    String updatedAt;
    String content;

    public ProductComment() {
    }

    public ProductComment(int pcid, int pid, int userid, String name, String email, String title, double rating, String image, String createdAt, String updatedAt, String content) {
        this.pcid = pcid;
        this.pid = pid;
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.title = title;
        this.rating = rating;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    
    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
    
    Connection cnn; //dùng để kết nối
    Statement stm; //Thực thi các câu lệnh sql
    PreparedStatement pstm;
    ResultSet rs; //Lưu trữ và xử lý dữ liệu

    private void connect() {
        try {
            cnn = (new DBContext()).connection;
            if (cnn != null) {
                System.out.println("Connect product success");
            } else {
                System.out.println("Connect fail");
            }
        } catch (Exception e) {

        }
    }
    
    //add comment
    public void addComment(int pid, int userid, String name, String email, String title, double rating, String image, String content) {
        try {
            connect();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            String datetime = dtf.format(now);
            String strAdd = "insert into ProductComment(productId, userId, name, email, title, rating, image, createdAt, "
                    + "updatedAt, content) values (?,?,?,?,?,?,?,?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setInt(1, pid);
            pstm.setInt(2, userid);
            pstm.setString(3, name);
            pstm.setString(4, email);
            pstm.setString(5, title);
            pstm.setDouble(6, rating);
            pstm.setString(7, image);
            pstm.setString(8, datetime);
            pstm.setString(9, datetime);
            pstm.setString(10, content);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("addComment: " + e.getMessage());
        }
    }
    
    public List<ProductComment> getListProductComment(int id) {
        List<ProductComment> data = new ArrayList<ProductComment>();
        try {
            connect();
            String strSelect = "select * from ProductComment where productId = ? order by updatedAt desc";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int pcid = rs.getInt(1);
                int pid = rs.getInt(2);
                int userid = 0;
                if (rs.getString(3).isEmpty()) {
                    userid = rs.getInt(3);
                }
                String name = rs.getString(4);
                String email = rs.getString(5);
                String title = rs.getString(6);
                double rating = rs.getDouble(7);
                String image = "";  
                if (rs.getString(8).isEmpty()) {
                    image = rs.getString(8);
                }
                String createdAt = rs.getString(9);
                String updatedAt = rs.getString(10);
                String content = rs.getString(11);
                data.add(new ProductComment(pcid, pid, userid, name, email, title, rating, image, createdAt, updatedAt, content));
            }
            cnn.close();
            return data;
        } catch (Exception e) {
            System.out.println("getListProductComment: " + e.getMessage());
        }
        return null;
    }
    
    
}
