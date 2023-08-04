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
import java.util.ArrayList;
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
                System.out.println("Connect fail");
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

    public List<Product> getProductBegin(String substring) {
        List<Product> data = new ArrayList<Product>();
        try {
            connect();
            String strSelect = "select [id], [model], [categoryid], [name], [description], "
                    + "[sellPrice], [warranty], [sold], [view], [manufacturer], [createdAt], [updatedAt] from Product where [name] like ?";
            pstm = cnn.prepareStatement(strSelect);
            String pattern = '%' + substring + '%';
            pstm.setString(1, pattern);
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
                String createdAt = rs.getString(11);
                String updatedAt = rs.getString(12);
                Product p = new Product();
                List<String> imgList = p.getProductImageList(pid);
                if (!checkProductList(data, pid)) {
                    data.add(new Product(pid, model, categoryid, pname, description, imgList, sellPrice, warranty, sold, view, manufacturer, createdAt, updatedAt));
                }
            }
            cnn.close();
            return data;
        } catch (Exception e) {
            System.out.println("getBeginProduct: " + e.getMessage());
        }
        return null;
    }

    public List<Product> getListProductBySold() {
        List<Product> data = new ArrayList<Product>();
        try {
            connect();
            String strSelect = "select [id], [model], [categoryid], [name], [description], [sellPrice], "
                    + "[warranty], [sold], [view], [manufacturer], [createdAt], [updatedAt] from Product order by sold desc";
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
            System.out.println("getListProductBySold: " + e.getMessage());
        }
        return data;
    }

    public List<Product> getProductSearch(List<Product> data, String substring) {
        List<Product> dataSearch = new ArrayList<Product>();
        try {
            for (Product p : data) {
                if (p.getPname().toLowerCase().contains(substring.toLowerCase()) || 
                        p.getModel().toLowerCase().contains(substring.toLowerCase())) {
                    dataSearch.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("getProductSearch: " + e.getMessage());
        }
        return dataSearch;
    }

    public List<String> getListProductManufacturer() {
        List<String> data = new ArrayList<String>();
        try {
            connect();
            String strSelect = "select distinct [manufacturer] from Product";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                data.add(rs.getString(1));
            }
            cnn.close();
            return data;
        } catch (Exception e) {
            System.out.println("getListManufacturer: " + e.getMessage());
        }
        return null;
    }

    public List<Product> getListProductInPriceRange(List<Product> data, int min, int max) {
        List<Product> dataSearch = new ArrayList<Product>();
        try {
            for (Product p : data) {
                if (min <= p.getSellPrice() && p.getSellPrice() <= max) {
                    dataSearch.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("getListProductInPriceRange: " + e.getMessage());
        }
        return dataSearch;
    }

    public List<Product> getListProductByManufacturer(String manu) {
        List<Product> data = new ArrayList<Product>();
        try {
            connect();
            String strSelect = "select [id], [model], [categoryid], [name], [description], [sellPrice], [warranty], "
                    + "[sold], [view], [manufacturer], [createdAt], [updatedAt] from Product where [manufacturer] = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, manu);
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
                String createdAt = rs.getString(11);
                String updatedAt = rs.getString(12);
                Product p = new Product();
                List<String> imgList = p.getProductImageList(pid);
                data.add(new Product(pid, model, categoryid, pname, description, imgList, sellPrice, warranty, sold, view, manufacturer, createdAt, updatedAt));
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListProductByManufacturer: " + e.getMessage());
        }
        return data;
    }

    public List<Product> getListProductByCategory(List<Product> data, int cid) {
        List<Product> dataSearch = new ArrayList<Product>();
        try {
            for (Product p : data) {
                if (p.getCategoryid() == cid) {
                    dataSearch.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("getListProductByCategoryWithList: " + e.getMessage());
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
                String createdAt = rs.getString(11);
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
                String createdAt = rs.getString(11);
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

    public List<Product> getListProductByView() {
        List<Product> data = new ArrayList<Product>();
        try {
            connect();
            String strSelect = "select [id], [model], [categoryid], [name], [description], [sellPrice], [warranty], [sold], [view], "
                    + "[manufacturer], [createdAt], [updatedAt] from [Product] order by [view] desc";
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
            System.out.println("getListProductByView: " + e.getMessage());
        }
        return data;
    }

    public void updateView(int id) {
        try {
            connect();
            String strUpdate = "update Product set [view] = [view] + 1 where id = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("updateView: " + e.getMessage());
        }
    }

    public List<Product> getListProductBySellPrice() {
        List<Product> data = new ArrayList<Product>();
        try {
            connect();
            String strSelect = "select [id], [model], [categoryid], [name], [description], [sellPrice], "
                    + "[warranty], [sold], [view], [manufacturer], [createdAt], [updatedAt] from [Product] order by [sellPrice] asc";
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
            System.out.println("getListProductBySellPrice: " + e.getMessage());
        }
        return data;
    }

    public int getNumberPage() {
        try {
            connect();
            String strPage = "select count(*) from Product";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strPage);
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 9;
                if (total % 9 != 0) {
                    countPage++;
                }
                return countPage;
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("countPage: " + e.getMessage());
        }
        return 0;
    }

    public List<Product> getPaging(int index) {
        List<Product> data = new ArrayList<Product>();
        try {
            connect();
            String strPage = "select [id], [model], [categoryid], [name], [description], [sellPrice], [warranty], "
                    + "[sold], [view], [manufacturer], [createdAt], [updatedAt] from Product order by id OFFSET ? ROWS "
                    + "FETCH FIRST 10 ROWS ONLY";
            pstm = cnn.prepareStatement(strPage);
            pstm.setInt(1, (index - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                pid = rs.getInt(1);
                model = rs.getString(2);
                categoryid = rs.getInt(3);
                pname = rs.getString(4);
                description = rs.getString(5);
                sellPrice = rs.getDouble(6);
                warranty = rs.getInt(7);
                sold = rs.getInt(8);
                view = rs.getInt(9);
                manufacturer = rs.getString(10);
                createdAt = rs.getString(11);
                updatedAt = rs.getString(12);
                Product p = new Product();
                List<String> imgList = p.getProductImageList(pid);
                data.add(new Product(pid, model, categoryid, pname, description, imgList, sellPrice, warranty, sold, view, manufacturer, createdAt, updatedAt));
            }
            cnn.close();
            return data;
        } catch (Exception e) {
            System.out.println("paging: " + e.getMessage());
        }
        return null;
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

    @Override
    public String toString() {
        return "Product{" + "pid=" + pid + ", categoryid=" + categoryid + ", pname=" + pname + ", description=" + description + ", img=" + img + ", sellPrice=" + sellPrice + ", warranty=" + warranty + ", sold=" + sold + ", view=" + view + ", manufacturer=" + manufacturer + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

    public List<Product> getListProductByManufacturer(List<Product> data, String[] manufacturer) {
        List<Product> dataSearch = new ArrayList<Product>();
        try {
            for (Product p : data) {
                for (int i = 0; i < manufacturer.length; i++) {
                    if (p.getManufacturer().equals(manufacturer[i])) {
                        dataSearch.add(p);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("getListProductByManufacturerWithList: " + e.getMessage());
        }
        return dataSearch;
    }

}
