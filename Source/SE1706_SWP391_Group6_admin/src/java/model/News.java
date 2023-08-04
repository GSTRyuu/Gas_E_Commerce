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
            if (cnn == null) {
                System.out.println("Connect News fail!");
            }
        } catch (Exception e) {
        }
    }

    public News() {
    }

    public News(int id) {
        this.id = id;
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

    public News(int id, int adminId, int groupID, String title, String heading, String author, String image, String createAt, String updatedAt, String content, int view, String groupName, String adminName, int STT) {
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
        this.stt = STT;
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
//News

    public List<News> getListNews() {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news'";
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

    public News getNewsById(int nid) {
        try {
            connect();
            String selectStr = "select n.id, n.adminId, n.groupId,n.title, n.heading, n.author, n.[image],n.createdAt, n.updatedAt, n.content, ng.[name], \n"
                    + "(na.firstName + ' ' + na.lastName) as adminName, n.[view] from news n join NewsGroup ng on n.groupId = ng.id \n"
                    + "join [admin] na on n.adminId = na.id where n.id = ?";
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
                String createAt = convertDateTimeFormat(rs.getString(8));
                String updatedAt = convertDateTimeFormat(rs.getString(9));
                String content = rs.getString(10);
                String groupName = rs.getString(11);
                String adminName = rs.getString(12);
                int view = rs.getInt(13);
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
            String selectStr = "select n.id, n.adminId, n.groupId,n.title, n.heading, n.author, n.[image],n.createdAt, n.updatedAt, n.content, ng.[name], \n"
                    + "(na.firstName + ' ' + na.lastName) as adminName, n.[view] from news n join NewsGroup ng on n.groupId = ng.id \n"
                    + "join [admin] na on n.adminId = na.id where n.groupId = ? order by n.createdAt";
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
                String createAt = convertDateTimeFormat(rs.getString(8));
                String updatedAt = convertDateTimeFormat(rs.getString(9));
                String content = rs.getString(10);
                String groupName = rs.getString(11);
                String adminName = rs.getString(12);
                int view = rs.getInt(13);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNewsByGid: " + e.getMessage());
        }
        return data;
    }

    public List<News> getListNewsAndSearch(String search) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' and (n.title like '%" + search + "%' or n.author like '%" + search + "%')";
            pstm = cnn.prepareStatement(strSelect);
            //pstm.setString(1, search);
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
            System.out.println("getListNewsAndSearch 1 param:" + e.getMessage());
        }
        return data;
    }

    public List<News> getNewsByGidAndSearch(int gid, String search) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String selectStr = "select n.id, n.adminId, n.groupId,n.title, n.heading, n.author, n.[image],n.createdAt, n.updatedAt, n.content, ng.[name], \n"
                    + "(na.firstName + ' ' + na.lastName) as adminName, n.[view] from news n join NewsGroup ng on n.groupId = ng.id \n"
                    + "join [admin] na on n.adminId = na.id where n.groupId = ? and (n.title like '%" + search + "%' or n.author like '%" + search + "%')";
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
                String createAt = convertDateTimeFormat(rs.getString(8));
                String updatedAt = convertDateTimeFormat(rs.getString(9));
                String content = rs.getString(10);
                String groupName = rs.getString(11);
                String adminName = rs.getString(12);
                int view = rs.getInt(13);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getNewsByGidAndSearch 2 params: " + e.getMessage());
        }
        return data;
    }

    public void addNews(int aid, int gid, String title, String image, String heading, String author, String createAt, String content) {
        try {
            connect();
            String strAdd = "INSERT INTO [News]([adminId], [groupId], [title], [image], [heading], [author], [createdAt], [content])"
                    + " values (?,?,?,?,?,?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setInt(1, aid);
            pstm.setInt(2, gid);
            pstm.setString(3, title);
            pstm.setString(4, image);
            pstm.setString(5, heading);
            pstm.setString(6, author);
            pstm.setString(7, createAt);
            pstm.setString(8, content);
            pstm.execute();

            cnn.close();
        } catch (SQLException e) {
            System.out.println("addNews: " + e.getMessage());
        }
    }

    public void updateNews(int gid, String title, String image, String heading, String author, String updatedAt, String content, int id) {
        try {
            connect();
            String strUpdate = "UPDATE [dbo].[News]\n"
                    + "   SET [groupId] = ?\n"
                    + "      ,[title] = ?\n"
                    + "      ,[image] = ?\n"
                    + "      ,[heading] = ?\n"
                    + "      ,[author] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + "      ,[content] = ?\n"
                    + " WHERE id = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, gid);
            pstm.setString(2, title);
            pstm.setString(3, image);
            pstm.setString(4, heading);
            pstm.setString(5, author);
            pstm.setString(6, updatedAt);
            pstm.setString(7, content);
            pstm.setInt(8, id);
            pstm.execute();

            cnn.close();
        } catch (Exception e) {
            System.out.println("updateNews: " + e.getMessage());
        }
    }
    
    public void updatePolicy(String title, String updatedAt, String content, int id) {
        try {
            connect();
            String strUpdate = "UPDATE [dbo].[News]\n"
                    + "   SET [title] = ?\n"                    
                    + "      ,[updatedAt] = ?\n"
                    + "      ,[content] = ?\n"
                    + " WHERE id = ?";
            pstm = cnn.prepareStatement(strUpdate);            
            pstm.setString(1, title);
            pstm.setString(2, updatedAt);
            pstm.setString(3, content);
            pstm.setInt(4, id);
            pstm.execute();

            cnn.close();
        } catch (Exception e) {
            System.out.println("updatePolicy: " + e.getMessage());
        }
    }
    
    public void updateFooterContent( String title, String content, String updatedAt, int id) {
        try {
            connect();
            String strUpdate = "UPDATE [dbo].[News]\n"
                    + "   SET [title] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + "      ,[content] = ?\n"
                    + " WHERE id = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, title);
            pstm.setString(2, updatedAt);
            pstm.setString(3, content);
            pstm.setInt(4, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("updateFooterContent: " + e.getMessage());
        }
    }

    public void DeleteContents(String sid, String nid) {
        connect();
        int x = 1;
        try {
            
            String strSelect = "delete from News where id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sid);
            pstm.execute();
            
        } catch (Exception e) {
            System.out.println("DeleteContents: " + e.getMessage());
        }
        
        List<Integer> data = new ArrayList<Integer>();
        try {
            String strSelect = "select News.id from [News] where groupId = ? order by STT asc";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, nid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                data.add(id);
            }
        } catch (Exception e) {
            System.out.println("getListContentsByGid: " + e.getMessage());
        }

        try {
            for (Integer n : data) {
                String strUpdate = "UPDATE [News]\n"
                        + "   SET [STT] = ?\n"
                        + " WHERE id = ?";
                pstm = cnn.prepareStatement(strUpdate);
                pstm.setInt(1, x);
                pstm.setInt(2, n);
                pstm.execute();
                x++;
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("DeleteContents: " + e.getMessage());
        }
        
    }
    
       public void DeleteNews(String sid) {
        try {
            connect();
            String strSelect = "delete from News where id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sid);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("DeleteContents: " + e.getMessage());
        }

    }

    public void isSlideBanner(String id) {
        try {
            connect();
            String strSelect = "update [News] set [STT] = 1 where id= ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("isSlideBanner: " + e.getMessage());
        }

    }

    public void notSlideBanner(String id) {
        try {
            connect();
            String strSelect = "update [News] set [STT] = NULL where id= ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("notSlideBanner: " + e.getMessage());
        }

    }

    public List<News> getListByPagesAndGroupAndSort(int index, String grouped, String sorted) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName, n.STT from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' ";
            if (!grouped.equals("-1")) {
                strSelect += " and n.groupId = " + grouped;
            }
            if (!sorted.equals("-1")) {
                strSelect += " order by " + sorted + " desc \n";
            } else {
                strSelect += "order by n.id ";
            }
            strSelect += " Offset ? rows fetch first 5 rows only";
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
                int STT = rs.getInt(14);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName, STT));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListByPagesAndGroupAndSort:" + e.getMessage());
        }
        return data;
    }

    public List<News> getListContentByGid(int gid) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select News.id, News.adminId, News.groupId, News.title, News.STT, News.[image], News.link, News.createdAt, News.updatedAt, News.content from News\n"
                    + "join NewsGroup on News.groupId = NewsGroup.id\n"
                    + "where NewsGroup.id = ? ORDER BY News.STT ASC ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, gid);
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
            System.out.println("getListContentsByGid: " + e.getMessage());
        }
        return data;
    }

    public List<News> getListByPagesAndGroupAndSortAndSearch(int index, String grouped, String sorted, String search) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.AdminId, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName, n.STT from news n join [admin] a on a.id = n.adminid\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' ";
            if (!grouped.equals("-1")) {
                strSelect += " and n.groupId = " + grouped;
            }
            if (search != null) {
                strSelect += " and (n.Title like '%" + search + "%' or n.author like '%" + search + "%') ";
            } else {
                strSelect += " and n.title like '%%' ";
            }
            if (!sorted.equals("-1")) {
                strSelect += " order by " + sorted + " desc \n";
            } else {
                strSelect += "order by n.id ";
            }
            strSelect += " Offset ? rows fetch first 5 rows only";
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
                int STT = rs.getInt(14);
                data.add(new News(id, adminId, groupId, title, heading, author, image, createAt, updatedAt, content, view, groupName, adminName, STT));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListByPagesAndGroupAndSortAndSearch:" + e.getMessage());
        }
        return data;
    }

    //Contents
    public List<News> getListContentsByName(String name) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = " select News.id, News.adminId, News.groupId, News.title, News.STT, News.[image],"
                    + " News.link, News.createdAt, News.updatedAt, News.content from News \n"
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
            System.out.println("getListContentsByName: " + e.getMessage());
        }
        return data;
    }

    public News getContentById(int id) {
        try {
            connect();
            String strSelect = " select News.id, News.adminId, News.groupId, News.title, News.STT, News.[image], \n"
                    + "News.link, News.createdAt, News.updatedAt, News.content from News\n"
                    + "    join NewsGroup on News.groupId = NewsGroup.id\n"
                    + "    where News.id = ?\n"
                    + "    ORDER BY News.STT ASC";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, id);
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
                return new News(id, adminId, groupID, title, image, stt, link, createAt, updatedAt, content);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getContentsById: " + e.getMessage());
        }
        return null;
    }

    public void UpdateSTT(int[] order, String nid) {
        List<Integer> data = new ArrayList<Integer>();
        connect();
        int x = 1;
        try {
            
            for (int i = 0; i < order.length; i++) {
                String strSelect = "select id from [News] where groupId = ? and STT = ? ";
                pstm = cnn.prepareStatement(strSelect);
                pstm.setString(1, nid);
                pstm.setInt(2, order[i]);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    id = rs.getInt(1);
                    data.add(id);
                }
            }
        } catch (Exception e) {
            System.out.println("UpdateSTT_1: " + e.getMessage());
        }

        try {
            for (Integer n : data) {
                System.out.println(n);
                String strUpdate = "UPDATE [News]\n"
                        + "   SET [STT] = ?\n"
                        + " WHERE id = ?";
                pstm = cnn.prepareStatement(strUpdate);
                pstm.setInt(1, x);
                pstm.setInt(2, n);
                pstm.execute();
                x++;
            }
            System.out.println(x);
            cnn.close();
        } catch (Exception e) {
            System.out.println("UpdateSTT_2: " + e.getMessage());
        }
    }

    public void AddContents(String name, String adminID, int STT, String link, String cr, String gr) {
        try {
            connect();
            String strSelect = " insert into [News](adminId, groupId, title, STT, link, createdAt)\n"
                    + " values\n"
                    + " (?, ?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, adminID);
            pstm.setString(2, gr);
            pstm.setString(3, name);
            pstm.setInt(4, STT);
            pstm.setString(5, link);
            pstm.setString(6, cr);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("AddContents: " + e.getMessage());
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
                return outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    
    public News getContentsFootlinkContent() {
        try {
            connect();
            String strSelect = " select News.id, News.adminId, News.groupId, News.title, News.STT, News.[image],\n"
                    + "News.link, News.createdAt, News.updatedAt, News.content from News join NewsGroup on\n"
                    + "News.groupId = NewsGroup.id where NewsGroup.name = 'footerContent' ORDER BY News.STT ASC";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            if (rs.next()) {
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
                return new News(id, adminId, groupID, title, image, stt, link, createAt, updatedAt, content);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getContentsFootlinkContent: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", title=" + title + ", stt=" + stt + ", link=" + link + ", content=" + content + '}';
    }

}
