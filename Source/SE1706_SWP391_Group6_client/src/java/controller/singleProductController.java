/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.News;
import model.Product;
import model.ProductComment;

/**
 *
 * @author Admin
 */
public class singleProductController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("productId"));
        Product p = new Product();
        ProductComment pc = new ProductComment();
        News n = new News();
        Category c = new Category();
        p = p.getProductByID(id);
        c = c.getCategoryByID(p.getCategoryid());
        req.setAttribute("p", p);
        req.setAttribute("c", c);
        List<Product> pList = p.getListProductBySold();
        req.setAttribute("defendNews", n.getListNews());
        req.setAttribute("pList", pList);
        req.setAttribute("cList", c.getListCategory());
        //Hiển thị list comment       
        //Nếu empty báo lỗi
        if (req.getParameter("name").isEmpty() || req.getParameter("email").isEmpty() || req.getParameter("title").isEmpty() || req.getParameter("rating").isEmpty() || req.getParameter("review").isEmpty()) {
            req.setAttribute("reviewMess", "*You must fill all information to submit your review");
        } else {           
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String title = req.getParameter("title");
            double rating = Double.parseDouble(req.getParameter("rating"));
            String review = req.getParameter("review");
            pc.addComment(id, 0, name, email, title, rating, "", review);
        }
        req.setAttribute("pcList", pc.getListProductComment(id));
        req.getRequestDispatcher("singleProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("productId"));
        Product p = new Product();
        News n = new News();
        Category c = new Category();
        p = p.getProductByID(id);
        c = c.getCategoryByID(p.getCategoryid());
        req.setAttribute("p", p);
        req.setAttribute("c", c);
        ProductComment pc = new ProductComment();
        List<Product> pList = p.getListProductBySold();
        req.setAttribute("pcList", pc.getListProductComment(id));
        req.setAttribute("defendNews", n.getListNews());
        req.setAttribute("pList", pList);
        req.setAttribute("cList", c.getListCategory());
        req.getRequestDispatcher("singleProduct.jsp").forward(req, resp);
    }
}
