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
import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.News;
import model.Order;
import model.OrderItem;

/**
 *
 * @author Dell
 */
public class orderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order o = new Order();
        OrderItem oi = new OrderItem();
        News n = new News();
        String act = req.getParameter("act");
        if (act.equals("view")) {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            o.getOrderDetail(orderId);
            req.setAttribute("o", o);
            req.setAttribute("oiList", oi.getListOrderItem(orderId));
            req.getRequestDispatcher("orderDetail.jsp").forward(req, resp);
        } else if (act.equals("sort") || act.equals("paging")) {
            int index;
            String sortBy = req.getParameter("sortBy");
            String filterBy = req.getParameter("filterBy");
            if (req.getParameter("index") == null) {
                index = 1;
            } else {
                index = Integer.parseInt(req.getParameter("index"));
                if (index < 1) {
                    index = 1;
                }
                if (index > o.getEndPage(sortBy, filterBy)) {
                    index = o.getEndPage(sortBy, filterBy);
                }
            }
            List<Order> oList = o.getListOrder(index, sortBy, filterBy);
            req.setAttribute("sortBy", sortBy);
            req.setAttribute("filterBy", filterBy);
            req.setAttribute("oList", oList);
            req.setAttribute("totalSold", oi.getTotalItemSold());
            req.setAttribute("totalEarn", o.getTotalSpent());
            req.setAttribute("currentPage", index);
            req.setAttribute("endPage", o.getEndPage(sortBy, filterBy));
            req.setAttribute("sortBys", n.getListContentsByName("orderSort"));
            req.setAttribute("filterBys", o.getListStatus());
            req.getRequestDispatcher("orderManagement.jsp").forward(req, resp);
        } else if (act.equals("search")) {
            String orderId = req.getParameter("orderId");
            if (orderId == null || orderId.isEmpty()) {
                resp.sendRedirect("order");
            } else {
                req.setAttribute("searchId", orderId);
                if (!orderId.contains("e")) {
                    req.setAttribute("oList", o.getListOrderById(orderId));
                } else {
                    req.setAttribute("oList", new ArrayList<>());
                }
                req.setAttribute("totalSold", oi.getTotalItemSold());
                req.setAttribute("totalEarn", o.getTotalSpent());
                req.setAttribute("sortBys", n.getListContentsByName("orderSort"));
                req.setAttribute("filterBys", o.getListStatus());
                req.getRequestDispatcher("orderManagement.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("acc") == null) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        Admin ch = (Admin) session.getAttribute("acc");
        if (!(ch.getRole().equals("Admin") || ch.getRole().equals("OrderManage"))) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        Order o = new Order();
        OrderItem oi = new OrderItem();
        News n = new News();
        req.setAttribute("sortBy", "default");
        req.setAttribute("filterBy", "all");
        req.setAttribute("sortBys", n.getListContentsByName("orderSort"));
        req.setAttribute("filterBys", o.getListStatus());
        req.setAttribute("totalSold", oi.getTotalItemSold());
        req.setAttribute("totalEarn", o.getTotalSpent());
        req.setAttribute("currentPage", 1);
        req.setAttribute("endPage", o.getEndPage("default","all"));
        req.setAttribute("oList", o.getListOrder(1, "default", "all"));
        req.getRequestDispatcher("orderManagement.jsp").forward(req, resp);
    }

}
