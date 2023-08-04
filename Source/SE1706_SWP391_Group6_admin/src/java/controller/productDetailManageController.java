/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class productDetailManageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        Category c = new Category();
        if (req.getParameter("act") == null) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("id", id);
            req.setAttribute("act", "update");
            req.setAttribute("p", p.getProductByID(id));
            req.setAttribute("cList", c.getListCategory());
            String imageFormat = "<p><img src=\"" + p.getProductByID(id).getImg().get(0) + "\" width=\"572\" height=\"322\" /></p>";
            req.setAttribute("imageFormat", imageFormat);
            req.getRequestDispatcher("productDetailManage.jsp").forward(req, resp);
        } else if (req.getParameter("act").equals("view")) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("id", id); 
            req.setAttribute("act", "view");
            req.setAttribute("c", c);
            req.setAttribute("p", p.getProductByID(id));
            req.setAttribute("cList", c.getListCategory());
            req.getRequestDispatcher("productDetailManage.jsp").forward(req, resp);
        } else if (req.getParameter("act").equals("addStock")){
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("id", id);
            req.setAttribute("p", p.getProductByID(id));
            req.getRequestDispatcher("productStockManage.jsp").forward(req, resp);
        } else {
            String model = req.getParameter("model");
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            int warranty = Integer.parseInt(req.getParameter("warranty"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String image = req.getParameter("image");
            double sellPrice = Double.parseDouble(req.getParameter("price"));
            String manufacturer = req.getParameter("manufacturer");
            String formatImage = extractImageSrc(image);
            if (formatImage == null) {
                formatImage = "";
            }
            if (req.getParameter("act").equals("update")) {
                int id = Integer.parseInt(req.getParameter("id"));
                p.updateProduct(id, model, categoryid, name, description, formatImage, sellPrice, p.getSold(), p.getView(), manufacturer);               
            } else if (req.getParameter("act").equals("add")) {
                p.addProduct(model, categoryid, name, description, formatImage, sellPrice, manufacturer, warranty);
            }
            resp.sendRedirect("productManage");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category c = new Category();
        req.setAttribute("cList", c.getListCategory());
        req.setAttribute("act", "add");
        req.getRequestDispatcher("productDetailManage.jsp").forward(req, resp);
    }

    public static String extractImageSrc(String html) {
        String src = null;
        int startIndex = html.indexOf("src=\"");
        if (startIndex != -1) {
            startIndex += "src=\"".length();
            int endIndex = html.indexOf("\"", startIndex);
            if (endIndex != -1) {
                src = html.substring(startIndex, endIndex);
            }
        }
        return src;
    }
}
