/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author Admin
 */
public class productManageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        List<Product> pList = p.getListProduct();
        int numberDisplay = 5;  
            int index = 1;
        if (req.getParameter("sort") == null) {
            p = p.getProductByID(Integer.parseInt(req.getParameter("id")));
            p.deleteProductImage();
            p.deleteProductComment();
            p.deleteProductInStock();
            p.deleteProduct();
            pList = p.getListProduct();
            if (req.getParameter("category") != null && !req.getParameter("category").equals("all")) {
                pList = p.getListProductByCategory(Integer.parseInt(req.getParameter("category")));
            }
            
        } else {
            if (req.getParameter("category") != null && !req.getParameter("category").equals("all")) {
                pList = p.getListProductByCategory(Integer.parseInt(req.getParameter("category")));
            }
            if (req.getParameter("search") != null) {
                pList = p.getProductSearch(pList, req.getParameter("search"));
            }
            if (pList != null) {
                switch (req.getParameter("sort")) {
                    case "name":
                        Collections.sort(pList, p.compareByName);
                        break;
                    case "view":
                        Collections.sort(pList, p.compareByView);
                        break;
                    case "latest":
                        Collections.sort(pList, p.compareByLatest);
                        break;
                    case "price":
                        Collections.sort(pList, p.compareByPrice);
                        break;
                    case "all":
                        break;
                }
                if (req.getParameter("type").equals("highToLow")) {
                    Collections.reverse(pList);
                }
            }
            req.setAttribute("sort", req.getParameter("sort"));
            req.setAttribute("type", req.getParameter("type"));
            req.setAttribute("search", req.getParameter("search"));
            numberDisplay = Integer.parseInt(req.getParameter("numberDisplay"));
            index = Integer.parseInt(req.getParameter("index"));
        }   
        Category c = new Category();
        List<Category> cList = c.getListCategory();       
        req.setAttribute("cid", req.getParameter("category"));
        req.setAttribute("pList", p.getPaging(pList, index, numberDisplay));
        req.setAttribute("count", p.getNumberPage(pList, numberDisplay));
        req.setAttribute("c", c);
        req.setAttribute("cList", cList);
        req.setAttribute("size", numberDisplay);
        req.setAttribute("total", pList.size());
        req.setAttribute("index", index);
        req.getRequestDispatcher("productManage.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        Category c = new Category();
        int pageSize = 5;
        int index = 1;
        List<Product> pList = p.getPaging(p.getListProduct(), index, pageSize);
        req.setAttribute("pList", pList);
        req.setAttribute("index", index);
        req.setAttribute("c", c);
        req.setAttribute("size", pageSize);
        req.setAttribute("total", p.getListProduct().size());
        req.setAttribute("count", p.getNumberPage(p.getListProduct(), pageSize));
        req.setAttribute("cList", c.getListCategory());
        req.getRequestDispatcher("productManage.jsp").forward(req, resp);
    }
}
