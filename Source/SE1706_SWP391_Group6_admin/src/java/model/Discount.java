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
public class Discount {

    private String code, name;
    private double amount;
    private String fromDate, toDate;
    private String description, type;

    private int userId, productId, number;
    private String productModel;
    Connection cnn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;

    private void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn == null) {
                System.out.println("Connect Discount fail!");
            }
        } catch (Exception e) {
        }
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Discount() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Discount(String code, String name, double amount, String description, String type) {
        this.code = code;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.type = type;
    }

    public Discount(String code, String fromDate, String toDate, int productId) {
        this.code = code;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.productId = productId;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Discount> getListDiscount() {
        List<Discount> data = new ArrayList<Discount>();
        try {
            connect();
            String strSelect = "SELECT [code], [name], [amount], [description], [type] FROM [dbo].[Discount]";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                data.add(new Discount(code, name, amount, description, type));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListDiscount:" + e.getMessage());
        }
        return data;
    }

    public List<Discount> getListDiscountByType(String t) {
        List<Discount> data = new ArrayList<Discount>();
        try {
            connect();
            String strSelect = "SELECT d.[code], d.[name], d.[amount], d.[description], d.[type] FROM [dbo].[Discount] d where d.[type] = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, t);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                data.add(new Discount(code, name, amount, description, type));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListDiscountByType:" + e.getMessage());
        }
        return data;
    }

    public List<Discount> getListDiscountByTypeAndSearch(String t, String search) {
        List<Discount> data = new ArrayList<Discount>();
        try {
            connect();
            String strSelect = "SELECT d.[code], d.[name], d.[amount], d.[description], d.[type] FROM [dbo].[Discount] d \n"
                    + "where d.[type] = ? and (d.[name] like '%" + search + "%' or d.code like '%" + search + "%')";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, t);

            rs = pstm.executeQuery();

            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                data.add(new Discount(code, name, amount, description, type));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListDiscountByTypeAndSearch:" + e.getMessage());
        }
        return data;
    }
    
    public List<Discount> getListDiscountByTypeAndSearchAndPage(int index, String t, String search) {
        List<Discount> data = new ArrayList<Discount>();
        try {
            connect();
            String strSelect = "SELECT d.[code], d.[name], d.[amount], d.[description], d.[type] FROM [dbo].[Discount] d where 1=1 ";
            if (t != null) {
                strSelect += " and d.[type] = '" + t + "' ";
            }
            if (search != null) {
                strSelect += " and (d.[name] like '%" + search + "%' or d.code like '%" + search + "%') ";
            } else {
                strSelect += " and d.[name] like '%%' ";
            }
            strSelect += " order by d.amount Offset ? rows fetch first 5 rows only";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (index - 1) * 5);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                data.add(new Discount(code, name, amount, description, type));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListDiscountByTypeAndSearchAndPage:" + e.getMessage());
        }
        return data;
    }
    
    public List<Discount> getListDiscountBySearch(String search) {
        List<Discount> data = new ArrayList<Discount>();
        try {
            connect();
            String strSelect = "SELECT d.[code], d.[name], d.[amount], d.[description], d.[type] FROM [dbo].[Discount] d \n"
                    + "where (d.[name] like '%" + search + "%' or d.code like '%" + search + "%')";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                data.add(new Discount(code, name, amount, description, type));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListDiscountByTypeAndSearch:" + e.getMessage());
        }
        return data;
    }
    
    public Discount getDiscountByCode(String c) {
        try {
            connect();
            String selectStr = "SELECT [code], [name], [amount], [description], [type] FROM [dbo].[Discount] where code = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, c);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getInt(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                return new Discount(code, name, amount, description, type);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getDiscountByCode: " + e.getMessage());
        }
        return null;
    }

    public Discount getProductDiscountByCode(String c) {
        try {
            connect();
            String selectStr = "SELECT [productId], [discountCode], [fromDate], [toDate] FROM [dbo].[product_discount] where [discountCode] = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, c);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String code = rs.getString(2);
                String fromDate = convertDateTimeFormat(rs.getString(3));
                String toDate = convertDateTimeFormat(rs.getString(4));
                return new Discount(code, fromDate, toDate, id);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getDiscountByCode: " + e.getMessage());
        }
        return null;
    }

    public void addDiscount(String code, String name, double amount, String description, String type) {
        try {
            connect();
            String strAdd = "INSERT INTO [Discount]([code], [name], [amount], [description], [type])\n"
                    + "values (?,?,?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, code);
            pstm.setString(2, name);
            pstm.setDouble(3, amount);
            pstm.setString(4, description);
            pstm.setString(5, type);
            pstm.execute();

            cnn.close();
        } catch (SQLException e) {
            System.out.println("addDiscount: " + e.getMessage());
        }
    }

    public void deleteDiscount(String cod) {
        try {
            connect();
            String strSelect = "delete from discount where code = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cod);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("deleteDiscount: " + e.getMessage());
        }
    }

    public void deleteUserDiscount(String cod) {
        try {
            connect();
            String strSelect = "delete from user_discount where discountCode = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cod);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("deleteUserDiscount: " + e.getMessage());
        }
    }

    public void deleteProductDiscount(String cod) {
        try {
            connect();
            String strSelect = "delete from product_discount where discountCode = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cod);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("deleteProductDiscount: " + e.getMessage());
        }
    }

    public void updateDiscount(String name, double amount, String description, String code) {
        try {
            connect();
            String strUpdate = "UPDATE [dbo].[Discount]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[amount] = ?\n"
                    + "      ,[description] = ?\n"
                    + " WHERE code = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, name);
            pstm.setDouble(2, amount);
            pstm.setString(3, description);
            pstm.setString(4, code);
            pstm.execute();

            cnn.close();
        } catch (Exception e) {
            System.out.println("updateDiscount: " + e.getMessage());
        }
    }

    public void updateProductDiscount(int productId, String fromDate, String toDate, String code) {
        try {
            connect();
            String strUpdate = "UPDATE [dbo].[product_discount]\n"
                    + "   SET [productId] = ?\n"
                    + "    ,[fromDate] = ?\n"
                    + "    ,[toDate] =  ?\n"
                    + " WHERE discountCode = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, productId);
            pstm.setString(2, fromDate);
            pstm.setString(3, toDate);
            pstm.setString(4, code);
            pstm.execute();

            cnn.close();
        } catch (Exception e) {
            System.out.println("updateProductDiscount: " + e.getMessage());
        }
    }

    public void addUserDiscount(int userId, String discountCode, int num) {
        try {
            connect();
            String strAdd = "insert into user_discount(userId,discountCode,number)\n"
                    + "values (?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setInt(1, userId);
            pstm.setString(2, discountCode);
            pstm.setInt(3, num);
            pstm.execute();

            cnn.close();
        } catch (SQLException e) {
            System.out.println("addUserDiscount: " + e.getMessage());
        }
    }

    public void addProductDiscount(int productId, String discountCode, String fromDate, String toDate) {
        try {
            connect();
            String strAdd = "INSERT INTO [product_discount](productId, discountCode, fromDate, toDate) values (?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setInt(1, productId);
            pstm.setString(2, discountCode);
            pstm.setString(3, fromDate);
            pstm.setString(4, toDate);
            pstm.execute();

            cnn.close();
        } catch (SQLException e) {
            System.out.println("addProductDiscount: " + e.getMessage());
        }
    }

    public int getProductIdByProductModel(String model) {
        try {
            connect();
            //"select id from Product p join product_discount pd on p.id = pd.productId where model = ?"
            String selectStr = "select id from Product where model = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, model);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int proId = rs.getInt(1);
                return proId;
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getProductIdByProductModel: " + e.getMessage());
        }
        return -1;
    }

    public String getProductModelByProductId(int id) {
        try {
            connect();
            String selectStr = "select model from Product where id = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String proModel = rs.getString(1);
                return proModel;
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getProductModelByProductId: " + e.getMessage());
        }
        return null;
    }

    public void updateUserDiscountNumber(int userId, String discountCode) {
        try {
            connect();
            String strUpdate = "update user_discount set number = number - 1 where userId = ? and discountCode = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, userId);
            pstm.setString(2, discountCode);
            pstm.execute();

            cnn.close();
        } catch (SQLException e) {
            System.out.println("updateUserDiscountNumber: " + e.getMessage());
        }
    }
    
    public List<String> getListProductModelByProductDiscountCode(String code) {
        List<String> list = new ArrayList<String>();
        try {
            connect();
            String selectStr = "select p.model from product_discount pd join Product p on pd.productId = p.id where pd.discountCode = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String proModel = rs.getString(1);
                list.add(proModel);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListProductModelByProductDiscountCode: " + e.getMessage());
        }
        return list;
    }

    public List<Integer> getListProductIdByDiscountCode(String discountCode) {
        List<Integer> data = new ArrayList<Integer>();
        try {
            connect();
            String strSelect = "select [productId] from product_discount where discountCode = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, discountCode);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int proId = rs.getInt(1);
                data.add(proId);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListProductIdByDiscountCode:" + e.getMessage());
        }
        return data;
    }
    
    public boolean checkAddProductModelOnActivateWithDiscount(String productModel, String fromDate, String toDate) {
        boolean check = false;
        try {
            connect();
            String selectStr = "select p.model from product_discount pd join Product p on pd.productId = p.id where p.model = ?\n"
                    + "	and ((fromDate >= ? and  fromDate <= ?) or (toDate >= ? and  toDate <= ?))";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, productModel);
            pstm.setString(2, fromDate);
            pstm.setString(3, toDate);
            pstm.setString(4, fromDate);            
            pstm.setString(5, toDate);
            rs = pstm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("checkProductModelOnActivateWithDiscount: " + e.getMessage());
        }

        return check;
    }

    public boolean checkUpdateProductModelOnActivateWithDiscount(String productModel, String fromDate, String toDate, String discountCode) {
        boolean check = false;
        try {
            connect();
            String selectStr = "select p.model from product_discount pd join Product p on pd.productId = p.id where p.model = ?\n"
                    + "	and ((fromDate >= ? and  fromDate <= ?) or (toDate >= ? and  toDate <= ?)) and pd.discountCode != ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, productModel);
            pstm.setString(2, fromDate);
            pstm.setString(3, toDate);
            pstm.setString(4, fromDate);
            pstm.setString(5, toDate);
            pstm.setString(6, discountCode);
            rs = pstm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("checkProductModelOnActivateWithDiscount: " + e.getMessage());
        }

        return check;
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
}
