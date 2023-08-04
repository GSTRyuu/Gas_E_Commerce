/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import model.Admin;
import model.News;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author fpt
 */
public class menuManageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        News con = new News();
        String id = req.getParameter("selectedNavId");
        con.DeleteContents(id, "1");
        resp.sendRedirect("menuManage?mod=5");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("acc") == null) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        Admin ch = (Admin) session.getAttribute("acc");
        if (!(ch.getRole().equals("Admin"))) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }

        News con = new News();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        switch (req.getParameter("mod")) {
            case "1":
                String orderJSON = req.getParameter("order");
                // Xử lý chuỗi JSON để chuyển đổi thành mảng
                int[] order = new Gson().fromJson(orderJSON, int[].class);
                
                con.UpdateSTT(order, "1");

                break;
            case "0":
                String menu = req.getParameter("menu");
                String link = req.getParameter("link");
                con.AddContents(menu, "1", con.getListContentsByName("navbar").size() + 1, link, dateFormat.format(date), "1");
                break;
            default:
                break;
        }
       
        session.setAttribute("navbar", con.getListContentsByName("navbar"));
        req.getRequestDispatcher("menuManage.jsp").forward(req, resp);
    }

}
