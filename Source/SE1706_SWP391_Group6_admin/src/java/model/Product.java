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
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Product {

    int pid;
    String model;
    int categoryid;
    String pname;
    String description;
    List<String> img;
    double sellPrice;
    int stockQuantity;
    int warranty;
    int sold;
    int view;
    String manufacturer;
    String createdAt;
    String updatedAt;

    public Product() {

    }

    public Product(int pid, String model, int categoryid, String pname, String description, List<String> img, double sellPrice, int warranty, int sold, int view, String manufacturer, String createdAt, String updatedAt) {
        this.pid = pid;
        this.model = model;
        this.categoryid = categoryid;
        this.pname = pname;
        this.description = description;
        this.img = img;
        this.sellPrice = sellPrice;
        this.warranty = warranty;
        this.sold = sold;
        this.view = view;
        this.manufacturer = manufacturer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getStockQuantity(int pid) {
        try {
            connect();
            String strSelect = "select count(productId) from ProductInStock "
                    + "where productId = ? and warrantyExp is null";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, pid);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getStockQuantity: " + e.getMessage());
        }
        return 0;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    Connection cnn; //dùng để kết nối
    Statement stm; //Thực thi các câu lệnh sql
    PreparedStatement pstm;
    ResultSet rs; //Lưu trữ và xử lý dữ liệu

    private void connect() {
        try {
            cnn = (new DBContext()).connection;
            if (cnn != null) {
            } else {
                System.out.println("Connect Product fail");
            }
        } catch (Exception e) {

        }
    }

    public List<String> getProductImageList(int pid) {
        List<String> image = new ArrayList<String>();
        try {
            connect();
            String strSelect = "select source from ProductImage \n"
                    + "where productId = ?\n"
                    + "order by id";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, pid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                image.add(rs.getString(1));
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListProductImage: " + e.getMessage());
        }
        return image;
    }

    public static String formatDate(String date, String pattern) {
        LocalDateTime datetime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        String newstring = datetime.format(DateTimeFormatter.ofPattern(pattern));
        return newstring;
    }

    public List<Product> getListProduct() {
        List<Product> data = new ArrayList<Product>();
        try {
            connect();
            String strSelect = "select [id], [model], [categoryid], [name], [description], [sellPrice], "
                    + "[warranty], [sold], [view], [manufacturer], [createdAt], [updatedAt] from Product";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                int pid = rs.getInt(1);
                String model = rs.getString(2);
                int categoryid = rs.getInt(3);
                String pname = rs.getString(4);
                String description = rs.getString(5);
                double sellPrice = rs.getDouble(6);
                int warranty = rs.getInt(7);
                int sold = rs.getInt(8);
                int view = rs.getInt(9);
                String manufacturer = rs.getString(10);
                String createdAt = rs.getString(11);
                String updatedAt = rs.getString(12);
                Product p = new Product();
                List<String> imgList = p.getProductImageList(pid);
                data.add(new Product(pid, model, categoryid, pname, description, imgList, sellPrice, warranty, sold, view, manufacturer, createdAt, updatedAt));
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListProduct: " + e.getMessage());
        }
        return data;
    }

    public boolean checkProductList(List<Product> a, int id) {
        for (Product e : a) {
            if (e.getPid() == id) {
                return true;
            }
        }
        return false;
    }

    public List<Product> getProductSearch(List<Product> data, String substring) {
        List<Product> dataSearch = new ArrayList<Product>();
        try {
            for (Product p : data) {
                if (p.getPname().toLowerCase().contains(substring.toLowerCase())) {
                    dataSearch.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("getProductSearch: " + e.getMessage());
        }
        return dataSearch;
    }

    public List<Product> getModelSearch(List<Product> data, String substring) {
        List<Product> dataSearch = new ArrayList<Product>();
        try {
            for (Product p : data) {
                if (p.getPname().toLowerCase().contains(substring.toLowerCase())) {
                    dataSearch.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("getModelSearch: " + e.getMessage());
        }
        return dataSearch;
    }
    
    public List<Product> getListProductByCategory(int cid) {
        List<Product> data = new ArrayList<Product>();
        try {
            connect();
            String strSelect = "select [id], [model], [categoryid], [name], [description], [sellPrice], [warranty], "
                    + "[sold], [view], [manufacturer], [createdAt], [updatedAt] from Product where categoryid=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, cid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt(1);
                String model = rs.getString(2);
                int categoryid = rs.getInt(3);
                String pname = rs.getString(4);
                String description = rs.getString(5);
                double sellPrice = rs.getDouble(6);
                int warranty = rs.getInt(7);
                int sold = rs.getInt(8);
                int view = rs.getInt(9);
                String manufacturer = rs.getString(10);
                String createdAt = formatDate(rs.getString(11), "dd/MM/yyyy");
                String updatedAt = rs.getString(12);
                Product p = new Product();
                List<String> imgList = p.getProductImageList(pid);
                data.add(new Product(pid, model, categoryid, pname, description, imgList, sellPrice, warranty, sold, view, manufacturer, createdAt, updatedAt));
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListProductByCategory: " + e.getMessage());
        }
        return data;
    }

    public Product getProductByID(int pid) {
        try {
            connect();
            String strSelect = "select [id], [model], [categoryid], [name], [description], [sellPrice], [warranty], "
                    + "[sold], [view], [manufacturer], [createdAt], [updatedAt] from Product where id=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, pid);
            rs = pstm.executeQuery();
            if (rs.next()) {
                pid = rs.getInt(1);
                String model = rs.getString(2);
                int categoryid = rs.getInt(3);
                String pname = rs.getString(4);
                String description = rs.getString(5);
                double sellPrice = rs.getDouble(6);
                int warranty = rs.getInt(7);
                int sold = rs.getInt(8);
                int view = rs.getInt(9);
                String manufacturer = rs.getString(10);
                String createdAt = formatDate(rs.getString(11), "dd/MM/yyyy");
                String updatedAt = rs.getString(12);
                Product p = new Product();
                List<String> imgList = p.getProductImageList(pid);
                return new Product(pid, model, categoryid, pname, description, imgList, sellPrice, warranty, sold, view, manufacturer, createdAt, updatedAt);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListProductById: " + e.getMessage());
        }
        return null;
    }

    public void updateProduct(int pid, String model, int categoryid, String pname, String description, String image, double sellPrice, int sold, int view, String manufacturer) {
        try {
            connect();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            String datetime = dtf.format(now);
            String strUpdate = "update Product set model = ?, categoryid=?, name=?, description=?, sellPrice=?, "
                    + "sold=?, [view]=?, manufacturer=?"
                    + ", updatedAt=? where id=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, model);
            pstm.setInt(2, categoryid);
            pstm.setString(3, pname);
            pstm.setString(4, description);
            pstm.setDouble(5, sellPrice);
            pstm.setInt(6, sold);
            pstm.setInt(7, view);
            pstm.setString(8, manufacturer);
            pstm.setString(9, datetime);
            pstm.setInt(10, pid);
            pstm.execute();
            strUpdate = "update ProductImage set source=? where productId=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, image);
            pstm.setInt(2, pid);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Update: " + e.getMessage());
        }
    }

    public void deleteProduct() {
        try {
            connect();
            String strDelete = "delete from Product where id=?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setInt(1, pid);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Delete: " + e.getMessage());
        }
    }

    public void addProduct(String model, int categoryid, String pname, String description, String image, double sellPrice, String manufacturer, int warranty) {
        try {
            connect();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            String datetime = dtf.format(now);
            String strAdd = "insert into Product(model, categoryid, name, description, sellPrice, sold, [view], manufacturer, createdAt, warranty) values (?,?,?,?,?,?,?,?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, model);
            pstm.setDouble(2, categoryid);
            pstm.setString(3, pname);
            pstm.setString(4, description);
            pstm.setDouble(5, sellPrice);
            pstm.setInt(6, 0);
            pstm.setInt(7, 0);
            pstm.setString(8, manufacturer);
            pstm.setString(9, datetime);
            pstm.setInt(10, warranty);
            pstm.execute();
            String select = "select [id] from Product where [model]=?";
            pstm = cnn.prepareStatement(select);
            pstm.setString(1, model);
            rs = pstm.executeQuery();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            strAdd = "insert into ProductImage(productId, source) values (?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setInt(1, id);
            pstm.setString(2, image);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Add: " + e.getMessage());
        }
    }

    public int getNumberPage(List<Product> data, int numberDisplay) {
        try {
            int total = data.size();
            int countPage = 0;
            countPage = total / numberDisplay;
            if (total % numberDisplay != 0) {
                countPage++;
            }
            return countPage;

        } catch (Exception e) {
            System.out.println("countPage: " + e.getMessage());
        }
        return 0;
    }

    public List<Product> getPaging(List<Product> data, int index, int numberDisplay) {
        List<Product> pageData = new ArrayList<Product>();
        try {
            for (int i = (index - 1) * numberDisplay; i < index * numberDisplay; i++) {
                if (i == data.size()) {
                    break;
                }
                pageData.add(data.get(i));
            }
            return pageData;
        } catch (Exception e) {
            System.out.println("paging: " + e.getMessage());
        }
        return null;
    }
    
    public void deleteProductImage() {
        try {
            connect();
            String strDelete = "delete from ProductImage where productId=?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setInt(1, pid);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("DeleteImage: " + e.getMessage());
        }
    }

    public void deleteProductComment() {
        try {
            connect();
            String strDelete = "delete from ProductComment where productId=?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setInt(1, pid);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("DeleteComment: " + e.getMessage());
        }
    }

    public void deleteProductInStock() {
        try {
            connect();
            String strDelete = "delete from ProductInStock where productId=?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setInt(1, pid);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("DeleteInStock: " + e.getMessage());
        }
    }
    public void addStockProduct(String serial, int productid) {
        try {
            connect();
            String strAdd = "insert into ProductInStock(serial, productid, mfgDate) values (?,?, GETDATE())";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, serial);
            pstm.setInt(2, productid);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("addStock: " + e.getMessage());
        }
    }
    
    public boolean checkStockProduct (String serial) {
        try {
            connect();
            String strAdd = "select * from ProductInStock where serial like ?";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, serial);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("checkStock: " + e.getMessage());
        }
        return false;
    }

    public Comparator<Product> compareByView = (Product o, Product o1) -> {
        double d = o.getView() - o1.getView();
        if (d > 0) {
            return 1;
        } else if (d < 0) {
            return -1;
        } else {
            return o.getCreatedAt().compareTo(o1.getCreatedAt());
        }
    };

    public Comparator<Product> compareByLatest = (Product o, Product o1) -> {
        double d = o1.getCreatedAt().compareTo(o.getCreatedAt());
        if (d > 0) {
            return 1;
        } else if (d < 0) {
            return -1;
        } else {
            return 0;
        }
    };

    public Comparator<Product> compareByName = (Product o, Product o1) -> {
        double d = o1.getPname().compareTo(o.getPname());
        if (d > 0) {
            return 1;
        } else if (d < 0) {
            return -1;
        } else {
            return 0;
        }
    };

    public Comparator<Product> compareByPrice = (Product o, Product o1) -> {
        double d = o1.getSellPrice() - o.getSellPrice();
        if (d > 0) {
            return 1;
        } else if (d < 0) {
            return -1;
        } else {
            return 0;
        }
    };
    
    public int getProductIDByModel(String part) {
        try {
            connect();
            String strSelect = "select [id] from Product where model like ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, part);
            rs = pstm.executeQuery();
            if (rs.next()) {                
                return rs.getInt(1);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListProductById: " + e.getMessage());
        }
        return 0;
    }
}
