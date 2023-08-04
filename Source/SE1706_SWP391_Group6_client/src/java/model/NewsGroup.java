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
public class NewsGroup {
    private int id;
    private String name;

    public NewsGroup() {
    }

    public NewsGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    public List<NewsGroup> getListNewsGroup() {
        List<NewsGroup> data = new ArrayList<NewsGroup>();
        try {
            connect();
            String strSelect = "select * from newsgroup where [type] = 'news' and [name] != 'Policy'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                data.add(new NewsGroup(id, name));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNewsGroup: " + e.getMessage());
        }
        return data;
    }
    
    public NewsGroup getNewsGroupById(int gid) {
        try {
            connect();
            String selectStr = "select id, [name] from newsGroup where id = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setInt(1, gid);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                return new NewsGroup(id, name);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getNewsGroupById: " + e.getMessage());
        }
        return null;
    }
}
