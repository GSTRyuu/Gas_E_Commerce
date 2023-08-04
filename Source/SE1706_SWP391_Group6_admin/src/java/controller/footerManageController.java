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
import java.util.Date;
import model.Admin;
import model.News;
import model.NewsGroup;

/**
 *
 * @author fpt
 */
public class footerManageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        News con = new News();
        String id = req.getParameter("selectedNavId");
        String nid = req.getParameter("ngid");
        con.DeleteContents(id, nid);
        resp.sendRedirect("footerManage?mod=5");
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
        NewsGroup ng = new NewsGroup();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        switch (req.getParameter("mod")) {
            case "1":
                String orderJSON = req.getParameter("order");
                String ngid = req.getParameter("ngid");
                // Xử lý chuỗi JSON để chuyển đổi thành mảng
                int[] order = new Gson().fromJson(orderJSON, int[].class);
                con.UpdateSTT(order, ngid);
                break;
            case "0":
                String menu = req.getParameter("menu");
                String link = req.getParameter("link");
                String ngn = req.getParameter("ngn");
                int countSTT;
                if(ngn.equals("4")){
                    countSTT = con.getListContentsByName("Policies").size() + 1;
                }else{
                    countSTT = con.getListContentsByName("About Us").size() + 1;
                }
                con.AddContents(menu, "1", countSTT, link, dateFormat.format(date), ngn);
                break;
            default:
                break;
        }
        session.setAttribute("footlink1", con.getListContentsByName("Policies"));
        session.setAttribute("footlink11", con.getListContentsByName("About Us"));
        req.setAttribute("ng", ng.getListNewsGroupEdit("footlink"));
        req.getRequestDispatcher("footerManage.jsp").forward(req, resp);

    }
}
