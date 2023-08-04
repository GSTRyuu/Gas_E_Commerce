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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author fpt
 */
public class Admin {

    private int id;
    private int roleId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String passwordHash;
    private String lastLogin;
    private String role;

    public Admin() {
    }

    public Admin(int id, String role, String firstName, String lastName, String mobile, String email, String passwordHash, String lastLogin) {
        this.id = id;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.passwordHash = passwordHash;
        this.lastLogin = lastLogin;

    }

    public Admin(int roleId, String firstName, String lastName, String mobile, String email, String passwordHash) {
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Admin(int roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
            if (cnn != null) {
                System.out.println("Connect success");
            } else {
                System.out.println("Connect fail");
            }
        } catch (Exception e) {
        }
    }

    public Admin getAdminByMobile(String mob) {
        try {
            connect();
            String strSelect = " select [Admin].id, [Role], firstName, lastName, mobile, email, passwordHash, lastLogin from [Admin] \n"
                    + "join [Role] on [Admin].roleId = [role].id where mobile = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, mob);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                role = rs.getString(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                mobile = rs.getString(5);
                email = rs.getString(6);
                passwordHash = rs.getString(7);
                lastLogin = convertDateTimeFormat(rs.getString(8));
                return new Admin(id, role, firstName, lastName, mobile, email, passwordHash, lastLogin);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return null;

    }
    
    public boolean checkExistMobile(String mobile) {
        try {
            connect();
            String strSelect = " select [Admin].id, [Role], firstName, lastName, mobile, email, passwordHash, lastLogin from [Admin] \n"
                    + "join [Role] on [Admin].roleId = [role].id where mobile = ?";
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
    

    public List<Admin> getListRole() {
        List<Admin> lrole = new ArrayList<>();
        try {
            connect();
            String select = "select id, [Role] from [Role] where role != 'Admin'";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                roleId = rs.getInt(1);
                role = rs.getString(2);
                lrole.add(new Admin(roleId, role));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStatus: " + e.getMessage());
        }
        return lrole;
    }
    
    public String getRoleById(int r) {
        try {
            connect();
            String select = "select [role] from [Role] where id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, r);
            rs = pstm.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStatus: " + e.getMessage());
        }
        return role;
    }
    
    public int getEndPage(String filterBy) {
        try {
            connect();
            if (filterBy.equals("all")) {
                String select = "select count(*) from [Admin] join [Role] on [Admin].roleId = [role].id "
                        + "where [role].[role] != 'Admin'";
                pstm = cnn.prepareStatement(select);
            } else {
                String select = "select count(*) from [Admin] join [Role] on [Admin].roleId = [role].id "
                        + "where [role].[role] = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, filterBy);
            }
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

    public int getEndPageSearch(String input) {
        try {
            connect();
            String select = "select count(*) from [Admin]\n"
                    + "join [Role] on [Admin].roleId = [role].id \n"
                    + "WHERE [Admin].firstName like '%" + input + "%' OR [Admin].lastName like '%" + input + "%'";
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
            System.err.println("getEndPagesSearch: " + e.getMessage());
        }
        return 0;
    }

    public List<Admin> getListStaff(int index, String filterBy) {
        List<Admin> aList = new ArrayList<>();
        try {
            connect();
            String select;
            if (filterBy.equals("all")) {
                select = "select [Admin].id, [Role], firstName, lastName, mobile, email, passwordHash, lastLogin "
                        + "from [Admin] \n"
                        + "join [Role] on [Admin].roleId = [role].id where role != 'Admin' ORDER BY [Admin].id\n"
                        + "OFFSET ? ROWS FETCH NEXT 4 ROWS only";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, (index - 1) * 4);
            } else {
                select = "select [Admin].id, [role], firstName, lastName, mobile, email, passwordHash, lastLogin from [Admin] \n"
                        + "join [Role] on [Admin].roleId = [role].id \n"
                        + "WHERE [role] = ?\n"
                        + "ORDER BY [Admin].id\n"
                        + "OFFSET ? ROWS FETCH NEXT 4 ROWS only";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, filterBy);
                pstm.setInt(2, (index - 1) * 4);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                role = rs.getString(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                mobile = rs.getString(5);
                email = rs.getString(6);
                passwordHash = rs.getString(7);
                lastLogin = convertDateTimeFormat(rs.getString(8));
                aList.add(new Admin(id, role, firstName, lastName, mobile, email, passwordHash, lastLogin));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStaff: " + e.getMessage());
        }
        return aList;
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

    public void updateLastLogin(String time, int id) {
        try {
            connect();
            String strUpdate = "update [Admin] set [lastLogin] = ? where id= ?";
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
            String strUpdate = "update [Admin] set [lastLogout] = ? where id= ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, time);
            pstm.setInt(2, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Update: " + e.getMessage());
        }
    }
     
     public void deleteAdmin(int id) {
        try {
            connect();
            String strSelect = "delete from [Admin] where id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, id);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("deleteAdmin: " + e.getMessage());
        }
    }

    public void updatePass(String pass, String index) {
        try {
            connect();
            String strUpdate = "update [Admin] set [passwordHash] = ? where id= ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, pass);
            pstm.setString(2, index);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Update: " + e.getMessage());
        }
    }

    public void updateRole(int code, int roleId) {
        try {
            connect();
            String update = "UPDATE [Admin] SET [Admin].roleId = ? \n"
                    + "   WHERE [Admin].id = ?";
            pstm = cnn.prepareStatement(update);
            pstm.setInt(1, roleId);
            pstm.setInt(2, code);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.err.println("updateRole: " + e.getMessage());
        }
    }

    public List<Admin> searchAdmin(int index, String input) {
        List<Admin> aList = new ArrayList<>();
        try {
            connect();
            String select = "select [Admin].id, [role], firstName, lastName, mobile, email, passwordHash, lastLogin from [Admin] \n"
                    + "join [Role] on [Admin].roleId = [role].id \n"
                    + "WHERE [Admin].firstName like ? OR [Admin].lastName like ? \n"
                    + "ORDER BY [Admin].id \n"
                    + "OFFSET ? ROWS FETCH NEXT 4 ROWS only";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, "%" + input + "%");
            pstm.setString(2, "%" + input + "%");
            pstm.setInt(3, index);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                role = rs.getString(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                mobile = rs.getString(5);
                email = rs.getString(6);
                passwordHash = rs.getString(7);
                lastLogin = convertDateTimeFormat(rs.getString(8));
                aList.add(new Admin(id, role, firstName, lastName, mobile, email, passwordHash, lastLogin));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("searchAdmin: " + e.getMessage());
        }
        return aList;
    }

    public void addStaff(int roleId, String fname, String lname, String mobile, String email, String pass) {
        try {
            connect();
            String strSelect = "insert into [Admin] (roleId, firstName, lastName, mobile, email, passwordHash)\n"
                    + "values\n"
                    + "(?, ?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, roleId);
            pstm.setString(2, fname);
            pstm.setString(3, lname);
            pstm.setString(4, mobile);
            pstm.setString(5, email);
            pstm.setString(6, pass);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("AddStaff: " + e.getMessage());
        }
    }
    
    

}
