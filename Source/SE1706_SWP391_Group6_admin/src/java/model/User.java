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
import java.util.Date;
import java.util.List;

/**
 *
 * @author fpt
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String passwordHash;
    private String registerAt;
    private String lastLogin;
    private double totalPrice;
    private int numOfOrder;

    public User() {
    }

    public User(String mobile, String email, String registerAt, String lastLogin, double totalPrice, int numOfOrder, int id) {
        this.mobile = mobile;
        this.email = email;
        this.registerAt = registerAt;
        this.lastLogin = lastLogin;
        this.totalPrice = totalPrice;
        this.numOfOrder = numOfOrder;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getRegisterAt() {
        return registerAt;
    }

    public void setRegisterAt(String registerAt) {
        this.registerAt = registerAt;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumOfOrder() {
        return numOfOrder;
    }

    public void setNumOfOrder(int numOfOrder) {
        this.numOfOrder = numOfOrder;
    }

    Connection cnn;  //ket noi
    Statement stm;   //thuc hien cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;    //luu tru va xu ly du lieu

    private void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn != null) {
                System.out.println("Connect success");
            } else {
                System.out.println("Connect fail");
            }
        } catch (Exception e) {
        }
    }
//Sign In===============================================================================================================================================================

    public void deleteUser(int id) {
        try {
            connect();
            String strSelect = "delete from [User] where id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, id);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("deleteUser: " + e.getMessage());
        }
    }

    public List<User> getListUser(int index) {
        List<User> aList = new ArrayList<>();
        try {
            connect();
            String select;
            select = "select [User].mobile, [User].email, count([Order].id), sum([Order].grandTotal), [User].registerAt, [User].lastLogin, [User].id from [User] join [Order] on [User].id = [Order].userId group by [User].mobile, [User].email, [User].registerAt, [User].lastLogin, [User].id, [Order].statusId having [Order].statusId not in (0, 1) ORDER BY [User].id OFFSET ? ROWS FETCH NEXT 4 ROWS only";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, (index - 1) * 4);
            rs = pstm.executeQuery();
            while (rs.next()) {
                mobile = rs.getString(1);
                email = rs.getString(2);
                numOfOrder = rs.getInt(3);
                totalPrice = rs.getDouble(4);
                registerAt = convertDateTimeFormat(rs.getString(5));
                lastLogin = convertDateTimeFormat(rs.getString(6));
                id = rs.getInt(7);
                aList.add(new User(mobile, email, registerAt, lastLogin, totalPrice, numOfOrder, id));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStaff: " + e.getMessage());
        }
        return aList;
    }

    public int getEndPage() {
        try {
            connect();
            String select = "select count(*) from [User] join [Order] on [User].id = [Order].userId group by [Order].statusId having [Order].statusId not in (0, 1)";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int endPage = rs.getInt(1) / 4;
                if (rs.getInt(1) % 4 != 0) {
                    endPage++;
                }
                return endPage;
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getEndPagesAdmin: " + e.getMessage());
        }
        return 0;
    }

    public int getListNewUser() {
        int count = 0;
        try {
            connect();
            String strSelect = "select [User].id "
                    + "from [User] "
                    +"where [User].firstname is not null ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count++;
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("Loi new user: " + e.getMessage());
        }

        return count;

    }

    public int countOnlineUser() {
        int count = 0;
        try {
            connect();
            String strSelect = "select count([User].id) from [User] where [User].lastLogout is NULL";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return count;

    }

    public void deleteUser(String index) {
        try {
            connect();
            String strDelete = "delete from [User] where id = ?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setString(1, index);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Delete: " + e.getMessage());
        }
    }

    public String convertDateTimeFormat(String inputDateTime) {
        if (inputDateTime == null) {
            return null;
        } else {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat outputFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            try {
                Date date = inputFormat.parse(inputDateTime);
                return outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

    }
}
