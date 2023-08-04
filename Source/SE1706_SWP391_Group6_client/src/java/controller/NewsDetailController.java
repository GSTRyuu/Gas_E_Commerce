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
import java.util.ArrayList;
import model.News;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import model.Category;

/**
 *
 * @author Nam
 */
public class NewsDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nid = req.getParameter("nid");
        req.getRequestDispatcher("detailNews").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        News n = new News();
        List<News> news1 = n.getListNewsByView();
        List<News> props = n.getListPromotions();

        int selectId = -1;

        for (News aNew : news1) {
            if (title.equalsIgnoreCase(toSlug(aNew.getTitle()))) {
                selectId = aNew.getId();
            }
        }
        for (News prop : props) {
            if (title.equalsIgnoreCase(toSlug(prop.getTitle()))) {
                selectId = prop.getId();
            }
        }
        
        news1 = n.getListNewsByView(selectId);
        News thatNew = n.getNewsById(selectId);
        Category c = new Category();
        req.setAttribute("cList", c.getListCategory());
        req.setAttribute("promotions", props);
        req.setAttribute("news", news1);
        req.setAttribute("selectNew", thatNew);
    
        req.getRequestDispatcher("newsDetail.jsp").forward(req, resp);
    }
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
