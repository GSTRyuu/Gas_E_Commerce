/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public User() {

    }

    public User(int id, String firstName, String lastName, String mobile, String email, String passwordHash, String registerAt, String lastLogin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.passwordHash = passwordHash;
        this.registerAt = registerAt;
        this.lastLogin = lastLogin;

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

    Connection cnn;  //ket noi
    Statement stm;   //thuc hien cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;    //luu tru va xu ly du lieu

    private void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn == null) {
                System.out.println("Connect User fail");
            }
        } catch (Exception e) {
        }
    }
//Sign In===============================================================================================================================================================

    public User getLogin(String u, String p) {
        try {
            connect();
            String strSelect = "select id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin from [User] where mobile = ? and passwordHash = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, u);
            pstm.setString(2, p);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                mobile = rs.getString(4);
                email = rs.getString(5);
                passwordHash = rs.getString(6);
                registerAt = convertDateTimeFormat(rs.getString(7));
                lastLogin = convertDateTimeFormat(rs.getString(8));
//                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                return new User(id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getLogin: " + e.getMessage());
        }

        return null;

    }

    
      public User getLoginGmail(String p) {
        try {
            connect();
            String strSelect = "select id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin from [User] where passwordHash = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, p);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                mobile = rs.getString(4);
                email = rs.getString(5);
                passwordHash = rs.getString(6);
                registerAt = convertDateTimeFormat(rs.getString(7));
                lastLogin = convertDateTimeFormat(rs.getString(8));
//                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                return new User(id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getLoginGmail: " + e.getMessage());
        }

        return null;

    }
//Sign Up===============================================================================================================================================================
    public boolean checkExistMobile(String mobile) {
        try {
            connect();
            String strSelect = "select id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin from [User] where mobile = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, mobile);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("checkExistMobile: " + e.getMessage());
        }
        return false;

    }
    
    public boolean checkExistGmailUser(String pass) {
        try {
            connect();
            String strSelect = "select id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin from [User] where passwordHash = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pass);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("checkExistGmailUser: " + e.getMessage());
        }
        return false;

    }

    public void signUp(String fname, String lname, String email, String mobile, String pass, String date) {
        try {
            connect();
            String strSelect = "insert into [User] (firstName, lastName, email, mobile, passwordHash, registerAt)\n"
                    + "values (?, ?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, fname);
            pstm.setString(2, lname);
            pstm.setString(3, email);
            pstm.setString(4, mobile);
            pstm.setString(5, pass);
            pstm.setString(6, date);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("signUp: " + e.getMessage());
        }
    }

//Check password==============================================================================================================================================================
    public String getEncryptedPassword(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public boolean verifyPassword(String input, String verify) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        String check = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return verify.equals(check);
    }

    public User getUserByMobile(String mob) {
        try {
            connect();
            String strSelect = "select id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin from [User] where mobile = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, mob);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                mobile = rs.getString(4);
                email = rs.getString(5);
                passwordHash = rs.getString(6);
                registerAt = convertDateTimeFormat(rs.getString(7));
                lastLogin = convertDateTimeFormat(rs.getString(8));
                return new User(id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin);

            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return null;
    }

    //Check email==============================================================================================================================================================
    private final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Update detail==============================================================================================================================================================
    public void updateUserDetail(String mobile, String fname, String lname, String email, int id) {
        try {
            connect();
            String strUpdate = "update [User] set [mobile] = ?, [firstName] = ?, [lastName] = ?, [email] = ? where id= ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, mobile);
            pstm.setString(2, fname);
            pstm.setString(3, lname);
            pstm.setString(4, email);
            pstm.setInt(5, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Update: " + e.getMessage());
        }
    }

    public void updateUserDetailAndPass(String mobile, String fname, String lname, String email, int id, String pass) {
        try {
            connect();
            String strUpdate = "update [User] set [mobile] = ?, [firstName] = ?, [lastName] = ?, [email] = ?, [passwordHash] = ? where id= ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, mobile);
            pstm.setString(2, fname);
            pstm.setString(3, lname);
            pstm.setString(4, email);
            pstm.setString(5, pass);
            pstm.setInt(6, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Update: " + e.getMessage());
        }
    }

    public void updatePass(String pass, String index) {
        try {
            connect();
            String strUpdate = "update [User] set [passwordHash] = ? where id= ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, pass);
            pstm.setString(2, index);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Update: " + e.getMessage());
        }
    }

    public User getUserById(int index) {
        try {
            connect();
            String strSelect = "select id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin from [User] where id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, index);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                mobile = rs.getString(4);
                email = rs.getString(5);
                passwordHash = rs.getString(6);
                registerAt = convertDateTimeFormat(rs.getString(7));
                lastLogin = convertDateTimeFormat(rs.getString(8));
                return new User(id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    public String convertDateTimeFormat(String inputDateTime) {
        if (inputDateTime == null) {
            return null;
        } else {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat outputFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            try {
                Date date = inputFormat.parse(inputDateTime);
                System.out.println(date);
                return outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    public void updateLastLogin(String time, int id) {
        try {
            connect();
            String strUpdate = "update [User] set [lastLogin] = ? where id= ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, time);
            pstm.setInt(2, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Update: " + e.getMessage());
        }
    }
     public void updateLastLogOut(String time, int id) {
        try {
            connect();
            String strUpdate = "update [User] set [lastLogout] = ? where id= ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, time);
            pstm.setInt(2, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Update: " + e.getMessage());
        }
    }
     
    public boolean checkExistEmail(String email) {
        try {
            connect();
            String strSelect = "select id, firstName, lastName, mobile, email, passwordHash, registerAt, lastLogin from [User] where email = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, mobile);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return false;

    } 

}