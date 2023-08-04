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
 * @author Nam
 */
public class NewsAdmin {
    String id, firstName, lastName, mobile, email, passwordHash, createAt, lastLogin;

    public NewsAdmin() {
    }

    public NewsAdmin(String id, String firstName, String lastName, String mobile, String email, String passwordHash, String createAt, String lastLogin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createAt = createAt;
        this.lastLogin = lastLogin;
        connect();
    }

    public NewsAdmin(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    Connection cnn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    
    private void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn != null) {
                System.out.println("Connect successfully!");
            } else {
                System.out.println("Connect fail!");
            }
        } catch (Exception e) {
        }
    }
    
    public List<NewsAdmin> getListNewsAdmins() {
        List<NewsAdmin> data = new ArrayList<NewsAdmin>();
        try {
            connect();
            String strSelect = "select id, firstName, lastName, mobile, email, passwordHash, createdAt, lastLogin from newsAdmin";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mobile = rs.getString(4);
                String email = rs.getString(5);
                String passwordHash = rs.getString(6);
                String createAt = rs.getString(7);
                String lastLogin = rs.getString(8);

                data.add(new NewsAdmin(id, firstName, lastName, mobile, email, passwordHash, createAt, lastLogin));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNewsAdmins:" + e.getMessage());
        }
        return data;
    }
    
    public NewsAdmin getNewsAdminById(){
        NewsAdmin cloneN = new NewsAdmin(id);
        try {
            connect();
            String strSelect = "select id, firstName, lastName, mobile, email, passwordHash, createdAt, lastLogin from NewsGroup "
                    + "where id='" + id + "' ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
//            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mobile = rs.getString(4);
                String email = rs.getString(5);
                String passwordHash = rs.getString(6);
                String createAt = rs.getString(7);
                String lastLogin = rs.getString(8);

                cloneN = new NewsAdmin(id, firstName, lastName, mobile, email, passwordHash, createAt, lastLogin);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getNewsAdminById:" + e.getMessage());
        }
        return cloneN;
    }
}
