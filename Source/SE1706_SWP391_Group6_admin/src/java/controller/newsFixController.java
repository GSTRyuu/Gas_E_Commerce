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
public class newsFixController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // do the task update or add news

        HttpSession s = req.getSession();
        String submitType = req.getParameter("submit");

        String author = req.getParameter("author").trim();
        String cateId = req.getParameter("cateId");
        String title = req.getParameter("title").trim();
        String heading = req.getParameter("heading").trim();
        String content = req.getParameter("content").trim();
        String image = req.getParameter("image");

        News n = new News();
        NewsGroup ng = new NewsGroup();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formatImage = extractImageSrc(image);
        if (formatImage == null) {
            formatImage = "";
        }
        if (submitType.equals("1")) {
            String newsId = req.getParameter("newsId");
            String cateName = ng.getNewsGroupById(Integer.parseInt(cateId)).getName();
            String updateAt = dtf.format(now);
            if (!heading.isEmpty() && !author.isEmpty() && !title.isEmpty() && !content.isEmpty()) {
                if (cateName.equalsIgnoreCase("Policy")) {
                    n.updatePolicy(title, updateAt, content, Integer.parseInt(newsId));
                } else {
                    n.updateNews(Integer.parseInt(cateId), title, formatImage, heading, author, updateAt, content, Integer.parseInt(newsId));
                }
                s.setAttribute("functionToast", "showToast('success','Update news successfully!')");
            } else {
                s.setAttribute("functionToast", "showToast('info','Some input(s) are blank!')");
                req.setAttribute("selectNews", n.getNewsById(Integer.parseInt(newsId)));
                req.setAttribute("groups", ng.getListNewsGroupWithoutPolicy());
                String imageFormat = "<p><img src=\"" + n.getNewsById(Integer.parseInt(newsId)).getImage() + "\" width=\"572\" height=\"322\" /></p>";
                req.setAttribute("imageFormat", imageFormat);
                req.getRequestDispatcher("newsDetailManagement.jsp").forward(req, resp);
                return;
            }

        } else {
            if (!heading.isEmpty() && !author.isEmpty() && !title.isEmpty() && !content.isEmpty()) {
                String createAt = dtf.format(now);
                Admin a = (Admin) s.getAttribute("acc");
                n.addNews(a.getId(), Integer.parseInt(cateId), title, formatImage, heading, author, createAt, content);
                s.setAttribute("functionToast", "showToast('success','Add news successfully!')");
            } else {
                s.setAttribute("functionToast", "showToast('info','Some input(s) are blank!')");
                req.setAttribute("heading", heading);
                req.setAttribute("title", title);
                req.setAttribute("content", content);
                req.setAttribute("author", author);
                req.setAttribute("heading", heading);
                String imageFormat = "<p><img src=\"" + formatImage + "\" width=\"572\" height=\"322\" /></p>";
                req.setAttribute("imageFormat", imageFormat);
                req.setAttribute("cateId", cateId);
                req.setAttribute("groups", ng.getListNewsGroupWithoutPolicy());
                req.getRequestDispatcher("newsDetailManagement.jsp").forward(req, resp);
                return;
            }
        }
        resp.sendRedirect("news");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public static String extractImageSrc(String html) {
        String src = null;
        if (html == null) {
            return "";
        }
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
