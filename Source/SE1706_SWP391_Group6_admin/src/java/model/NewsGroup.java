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
    private List<News> lnew;
    private String side;

    public NewsGroup() {
    }

    public NewsGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public NewsGroup(int id, String name, List<News> lnew, String side) {
        this.id = id;
        this.name = name;
        this.lnew = lnew;
        this.side = side;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<News> getLnew() {
        return lnew;
    }

    public void setLnew(List<News> lnew) {
        this.lnew = lnew;
    }

    Connection cnn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;

    private void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn == null) {
                System.out.println("Connect NewsGroup fail!");
            }
        } catch (Exception e) {
        }
    }

    public List<NewsGroup> getListNewsGroup() {
        List<NewsGroup> data = new ArrayList<NewsGroup>();
        try {
            connect();
            String strSelect = "select id, [name] from NewsGroup where [type] = 'news'";
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

    public List<NewsGroup> getListNewsGroupWithoutPolicy() {
        List<NewsGroup> data = new ArrayList<NewsGroup>();
        try {
            connect();
            String strSelect = "select id, [name] from newsgroup where [type] = 'news' and [name] != 'Policy'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                data.add(new NewsGroup(id, name));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNewsGroupWithoutPolicy: " + e.getMessage());
        }
        return data;
    }
    
    public NewsGroup getNewsGroupById(int gid) {
        try {
            connect();
            String selectStr = "select id, [name] from NewsGroup where id = ?";
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
    
    public NewsGroup getNewsGroupByName(String name1) {
        try {
            connect();
            String selectStr = "select id, [name] from newsGroup where [name] = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, name1);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                return new NewsGroup(id, name);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getNewsGroupByName: " + e.getMessage());
        }
        return null;
    }

    //News
    public List<NewsGroup> getListNewsGroupEdit(String type) {
        News n = new News();
        boolean check = false;
        List<NewsGroup> data = new ArrayList<NewsGroup>();
        try {
            connect();
            String selectStr = "select id, [name], [type] from newsGroup where type = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, type);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int nid = rs.getInt(1);
                String name = rs.getString(2);
                data.add(new NewsGroup(nid, name));
                check = true;
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNewsGroupEdit: " + e.getMessage());
        }
        return data;
    }
}
