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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Admin;
import model.News;
import model.NewsGroup;

/**
 *
 * @author Nam
 */
public class footerContentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String updateAt = dtf.format(now);
        News n = new News();
        n.updateFooterContent(title, content, updateAt, n.getContentsFootlinkContent().getId());

        resp.sendRedirect("footerContent");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // go to update news page
        HttpSession s = req.getSession();
        if (s.getAttribute("acc") == null) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        Admin ch = (Admin) s.getAttribute("acc");
        if (!(ch.getRole().equals("Admin") || ch.getRole().equals("NewsManage"))) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        News n = new News();
        News selectNews = n.getContentsFootlinkContent();
        req.setAttribute("selectNews", selectNews);
        req.getRequestDispatcher("footerContent.jsp").forward(req, resp);
    }

}
