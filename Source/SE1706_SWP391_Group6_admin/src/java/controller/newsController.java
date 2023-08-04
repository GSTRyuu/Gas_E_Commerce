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
import java.util.List;
import model.Admin;
import model.News;
import model.NewsGroup;

/**
 *
 * @author Nam
 */
public class newsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // do task of group, sort and page
        String sorted = req.getParameter("sortBy");
        String grouped = req.getParameter("groupBy");
        String page = req.getParameter("page");
        String search = req.getParameter("search");
        if (search.isEmpty()) {
            search = "";
        }
        if (page == null || page.equals("0")) {
            page = "1";
        }
        News n = new News();
        NewsGroup ng = new NewsGroup();
        String sortedPhase;
        if (sorted.equals("0")) {
            sortedPhase = "-1";
        }else{
            sortedPhase = n.getContentById(Integer.parseInt(sorted)).getContent();
        }
//        if (sorted.equals("2")) {
//            sorted = "n.[view]";
//        } else if (sorted.equals("1")) {
//            sorted = "n.createdAt";
//        } else {
//            sorted = "-1";
//        }
        if (grouped.equals("0")) {
            grouped = "-1";
        }
        req.setAttribute("count", calThePage(5, Integer.parseInt(grouped), search));
        req.setAttribute("sortBy", Integer.parseInt(req.getParameter("sortBy")));
        req.setAttribute("groupBy", Integer.parseInt(req.getParameter("groupBy")));
        req.setAttribute("search", req.getParameter("search"));
        if (Integer.parseInt(page) > calThePage(5, Integer.parseInt(grouped), search)) {
            page = calThePage(5, Integer.parseInt(grouped), search) + "";
        }
        req.setAttribute("sorts", n.getListContentsByName("newsSort"));
        req.setAttribute("page", page);
        req.setAttribute("groups", ng.getListNewsGroup());
//        req.setAttribute("news", n.getListByPagesAndGroupAndSort(Integer.parseInt(page), grouped, sorted));
        req.setAttribute("news", n.getListByPagesAndGroupAndSortAndSearch(Integer.parseInt(page), grouped, sortedPhase, search));
        req.getRequestDispatcher("news.jsp").forward(req, resp);
    }
    public static void main(String[] args) {
        News n = new News();
        List<News> news = n.getListByPagesAndGroupAndSortAndSearch(1, "-1", "-1", null);
        for (News aNew : news) {
            System.out.println(n.getStt());
        }
        System.out.println(n.getNewsById(34).getStt());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // display the news page with default page, group, sort
        HttpSession s = req.getSession();
        if (s.getAttribute("acc") == null) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        Admin ch = (Admin) s.getAttribute("acc");
        if (!(ch.getRole().equals("Admin") || ch.getRole().equals("NewsManage"))) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        s.removeAttribute("updateNewsId");
        News n = new News();
        NewsGroup ng = new NewsGroup();
        List<NewsGroup> listng = ng.getListNewsGroup();
        List<News> listsort = n.getListContentsByName("newsSort");

        req.setAttribute("page", "1");
        req.setAttribute("count", calThePage(5, -1, ""));
        req.setAttribute("groups", listng);
        req.setAttribute("sorts", listsort);
        //req.setAttribute("news", n.getListByPagesAndGroupAndSort(1, "-1", "-1"));
        req.setAttribute("news", n.getListByPagesAndGroupAndSortAndSearch(1, "-1", "-1", null));
        
        req.setAttribute("groupBy", "0");
        req.setAttribute("sortBy", "0");
        req.setAttribute("search", null);
        req.getRequestDispatcher("news.jsp").forward(req, resp);
    }

    public int calThePage(int sizePage, int gid, String search) {
        News n = new News();
        int pages = 0;
        int countNews = n.getListNewsAndSearch(search).size();
        if (gid != -1) {
            countNews = n.getNewsByGidAndSearch(gid, search).size();
        }
        if (countNews % sizePage == 0) {
            pages = countNews / sizePage;
        } else {
            pages = countNews / sizePage + 1;
        }
        return pages;
    }
}
