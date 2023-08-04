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
import model.Admin;
import model.News;
import model.NewsGroup;

/**
 *
 * @author Nam
 */
public class newsDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession();
        s.setAttribute("updateNewsId", req.getParameter("updateNewsId"));
        resp.sendRedirect("newsDetail");
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
        String nid = (String) s.getAttribute("updateNewsId");
        News n = new News();
        NewsGroup ng = new NewsGroup();
        if (nid == null) {
            n = null;
        } else {
            n = n.getNewsById(Integer.parseInt(nid));
            String imageFormat = "<p><img src=\"" + n.getImage() + "\" width=\"572\" height=\"322\" /></p>";
            req.setAttribute("imageFormat", imageFormat);
        }
        req.setAttribute("selectNews", n);
        req.setAttribute("groups", ng.getListNewsGroupWithoutPolicy());
        req.getRequestDispatcher("newsDetailManagement.jsp").forward(req, resp);
    }
}
