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
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import model.Category;
import model.News;
import model.NewsGroup;

/**
 *
 * @author Nam
 */
public class NewsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewId = req.getParameter("viewNewsId");
        News n = new News();
        n.updateView(Integer.parseInt(viewId));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String groupName = req.getParameter("groupName");   
        if (page == null) {
            page = "1";
        }
        NewsGroup ng1 = new NewsGroup();
        List<NewsGroup> groups = ng1.getListNewsGroup();
        int gid = -1;
        News n = new News();
        List<News> props = n.getListPromotions();
        Category c = new Category();
        req.setAttribute("cList", c.getListCategory());
        req.setAttribute("promotions", props);
        req.setAttribute("defendNews", n.getListNewsByView());
        if (groupName == null || groupName.isEmpty()) {
            req.setAttribute("news", n.getListByPages(Integer.parseInt(page)));
            req.setAttribute("groups", groups);
            req.setAttribute("count", calThePage(5, gid));
        } else {
            for (NewsGroup group : groups) {
                if (groupName.equals(toSlug(group.getName()))) {
                    gid = group.getId();
                }
            }
            if (gid == -1) {                
                req.setAttribute("news", n.getListByPages(Integer.parseInt(page)));
            } else {                
                req.setAttribute("news", n.getListByPagesAndGroup(Integer.parseInt(page), gid));
            }
            req.setAttribute("cList", c.getListCategory());
            req.setAttribute("groups", groups);
            req.setAttribute("count", calThePage(5, gid));
            req.setAttribute("selectGroup", ng1.getNewsGroupById(gid));
            String pageGroup = ng1.getNewsGroupById(gid).getName();
            if (pageGroup != null) {
                req.setAttribute("pageGroup", toSlug(ng1.getNewsGroupById(gid).getName()));
            }
        }
        req.setAttribute("page", page);
        req.getRequestDispatcher("news.jsp").forward(req, resp);
    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static int calThePage(int sizePage, int gid) {
        News n = new News();
        int pages = 0;        
        int countNews = n.getListNews().size();

        if (gid != -1) {
            countNews = n.getNewsByGid(gid).size();
        }
        if (countNews % sizePage == 0) {
            pages = countNews / sizePage;
        } else {
            pages = countNews / sizePage + 1;
        }
        return pages;
    }

}
