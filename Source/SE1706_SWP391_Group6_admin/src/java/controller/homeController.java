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
import model.News;
import model.Order;
import model.OrderItem;
import model.User;

/**
 *
 * @author fpt
 */
public class homeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("acc") == null) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        User u = new User();
        News con = new News();
        Order o = new Order();
        OrderItem oi = new OrderItem();

        //Get News contents
        session.setAttribute("footerContent", con.getListContentsByName("footerContent"));
        session.setAttribute("banner", con.getListContentsByName("banner"));
        session.setAttribute("userNavigation", con.getListContentsByName("usernavigation"));
        session.setAttribute("categories", con.getListContentsByName("categories"));

        req.setAttribute("newU", u.getListNewUser());
        //Get Online User
        int countOnlineUser = u.countOnlineUser();
        req.setAttribute("count", countOnlineUser);
        req.setAttribute("totalSold", oi.getTotalItemSold());
        req.setAttribute("totalEarn", o.getTotalSpent());
        req.setAttribute("listEarning", o.getTotalSpentByMonth());
        req.setAttribute("listTank", oi.getTotalItemSoldByMonth(1));
        req.setAttribute("listStove", oi.getTotalItemSoldByMonth(2));
        req.setAttribute("listAccessories", oi.getTotalItemSoldByMonth(3));
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
