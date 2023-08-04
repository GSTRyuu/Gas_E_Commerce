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
import model.Category;
import model.News;
import model.Product;

/**
 *
 * @author fpt
 */
public class homeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        HttpSession session = req.getSession();
        Category c = new Category();
        News con = new News();
        session.setAttribute("banner", con.getListNewsBeingSlideShare());
        session.setAttribute("navbar", con.getListContentsByName("navbar"));
        session.setAttribute("footerContent", con.getListContentsByName("footerContent"));
        session.setAttribute("userNavigation", con.getListContentsByName("Policies"));
        session.setAttribute("categories", con.getListContentsByName("About Us"));
        req.setAttribute("cList", c.getListCategory());
        req.setAttribute("lastGas", p.getListProduct());
        req.setAttribute("viewGas", p.getListProductByView());
        req.setAttribute("soldGas", p.getListProductBySold());
        req.setAttribute("sellPriceGas", p.getListProductBySellPrice());
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

}
