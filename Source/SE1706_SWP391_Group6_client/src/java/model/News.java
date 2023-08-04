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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nam
 */
public class News {

    private int id, adminId, groupID;
    private String title, heading, author, image;
    private int view, stt;
    private String link;
    private String createAt, updatedAt;
    private String content, groupName, adminName;
    private Connection cnn;
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

    public News() {
    }

    public News(int id, int groupID, String title, String heading, String author, String image, int view, int stt, String createAt, String updatedAt, String content, String groupName, String link) {
        this.id = id;
        this.groupID = groupID;
        this.title = title;
        this.heading = heading;
        this.author = author;
        this.image = image;
        this.view = view;
        this.stt = stt;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.content = content;
        this.groupName = groupName;
        this.link = link;
    }

    public News(int id, int adminId, int groupID, String title, String heading, String author, String image, int view, int stt, String link, String createAt, String updatedAt, String content, String groupName, String adminName) {
        this.id = id;
        this.adminId = adminId;
        this.groupID = groupID;
        this.title = title;
        this.heading = heading;
        this.author = author;
        this.image = image;
        this.view = view;
        this.stt = stt;
        this.link = link;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.content = content;
        this.groupName = groupName;
        this.adminName = adminName;
    }

    public News(int id, int adminId, int groupID, String title, String heading, String author, String image, String createAt, String updatedAt, String content, int view, String groupName, String adminName) {
        this.id = id;
        this.adminId = adminId;
        this.groupID = groupID;
        this.title = title;
        this.heading = heading;
        this.author = author;
        this.image = image;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.content = content;
        this.view = view;
        this.groupName = groupName;
        this.adminName = adminName;
    }

    public News(int id, int adminId, int groupID, String title, String image, int stt, String link, String createAt, String updatedAt, String content) {
        this.id = id;
        this.adminId = adminId;
        this.groupID = groupID;
        this.title = title;
        this.image = image;
        this.stt = stt;
        this.link = link;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.content = content;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<News> getListNews() {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' and ng.[name] != 'Sales & Promotion' and ng.[name] != 'Policy' order by n.createdAt desc";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                int id = rs.getInt(1);
                int adminId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                int view = rs.getInt(8);
                String createAt = convertDateTimeFormat(rs.getString(9));
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String adminName = rs.getString(13);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNews:" + e.getMessage());
        }
        return data;
    }

    public List<News> getListNewsBeingSlideShare() {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], n.STT, n.link from news n join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' and n.stt = 1";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int groupId = rs.getInt(2);
                String title = rs.getString(3);
                String heading = rs.getString(4);
                String author = rs.getString(5);
                String image = rs.getString(6);
                int view = rs.getInt(7);
                String createAt = convertDateTimeFormat(rs.getString(8));
                String updatedAt = convertDateTimeFormat(rs.getString(9));
                String content = rs.getString(10);
                String groupName = rs.getString(11);
                int STT = rs.getInt(12);
                String link = rs.getString(13);
                data.add(new News(id, groupId, title, heading, author, image, view, STT, createAt, updatedAt, content, groupName, link));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNewsBeingSlideShare:" + e.getMessage());
        }
        return data;
    }

    public List<News> getListNewsByView() {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' and ng.[name] != 'Sales & Promotion' and ng.[name] != 'Policy' order by n.[view] desc";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                int id = rs.getInt(1);
                int adminId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                int view = rs.getInt(8);
                String createAt = convertDateTimeFormat(rs.getString(9));
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String adminName = rs.getString(13);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNews:" + e.getMessage());
        }
        return data;
    }

    public List<News> getListPolicies() {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[name] = 'Policy' order by n.[view] desc";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                int id = rs.getInt(1);
                int adminId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                int view = rs.getInt(8);
                String createAt = convertDateTimeFormat(rs.getString(9));
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String adminName = rs.getString(13);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNews:" + e.getMessage());
        }
        return data;
    }
    
    public List<News> getListNewsByView(int nid) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' and n.id != ? and ng.[name] != 'Sales & Promotion' and ng.[name] != 'Policy' order by n.[view] desc";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, nid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int adminId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                int view = rs.getInt(8);
                String createAt = convertDateTimeFormat(rs.getString(9));
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String adminName = rs.getString(13);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNews:" + e.getMessage());
        }
        return data;
    }

    public News getNewsById(int nid) {
        try {
            connect();
            String selectStr = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where n.id = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setInt(1, nid);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                int adminId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                int view = rs.getInt(8);
                String createAt = convertDateTimeFormat(rs.getString(9));
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String adminName = rs.getString(13);

                return new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getNewsById: " + e.getMessage());
        }
        return null;
    }

    public List<News> getNewsByGid(int gid) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String selectStr = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.id = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setInt(1, gid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int adminId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                int view = rs.getInt(8);
                String createAt = convertDateTimeFormat(rs.getString(9));
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String adminName = rs.getString(13);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getNewsById: " + e.getMessage());
        }
        return data;
    }

    public List<News> getListPromotions() {
        ArrayList<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[name] = 'Sales & Promotion' order by n.createdAt desc";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                int id = rs.getInt(1);
                int adminId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                int view = rs.getInt(8);
                String createAt = convertDateTimeFormat(rs.getString(9));
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String adminName = rs.getString(13);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListPromotions:" + e.getMessage());
        }
        return data;
    }

    public List<News> getListByPages(int index) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' and ng.[name] != 'Sales & Promotion' and ng.[name] != 'Policy' order by n.createdAt desc\n"
                    + "Offset ? rows\n"
                    + "fetch first 5 rows only";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (index - 1) * 5);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int adminId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                int view = rs.getInt(8);
                String createAt = convertDateTimeFormat(rs.getString(9));
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String adminName = rs.getString(13);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListByPages:" + e.getMessage());
        }
        return data;
    }

    public List<News> getListByPagesAndGroup(int index, int gid) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.id = ? order by n.createdAt desc\n"
                    + "Offset ? rows\n"
                    + "fetch first 5 rows only";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, gid);
            pstm.setInt(2, (index - 1) * 5);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int adminId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                int view = rs.getInt(8);
                String createAt = convertDateTimeFormat(rs.getString(9));
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String adminName = rs.getString(13);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListByPagesAndGroup:" + e.getMessage());
        }
        return data;
    }

    //Code contents
    public List<News> getListContentsByName(String name) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = " select News.id, News.adminId, News.groupId, News.title, News.STT, News.[image], News.link, News.createdAt, News.updatedAt, News.content from News \n"
                    + " join NewsGroup on News.groupId = NewsGroup.id\n"
                    + " where NewsGroup.name = ? \n"
                    + " ORDER BY News.STT ASC";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                adminId = rs.getInt(2);
                groupID = rs.getInt(3);
                title = rs.getString(4);
                stt = rs.getInt(5);
                image = rs.getString(6);
                link = rs.getString(7);
                createAt = convertDateTimeFormat(rs.getString(8));
                updatedAt = convertDateTimeFormat(rs.getString(9));
                content = rs.getString(10);
                data.add(new News(id, adminId, groupID, title, image, stt, link, createAt, updatedAt, content));
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListContents: " + e.getMessage());
        }
        return data;
    }

    

    
    public void updateView(int nid) {
        try {
            connect();
            String strUpdate = "update news set [view] = [view] + 1 where id = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, nid);
            pstm.execute();

            cnn.close();
        } catch (Exception e) {
            System.out.println("updateView: " + e.getMessage());
        }
    }

    public String convertDateTimeFormat(String inputDateTime) {
        if (inputDateTime == null) {
            return null;
        } else {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat outputFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            try {
                java.util.Date date = inputFormat.parse(inputDateTime);
                System.out.println(date);
                return outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

}
