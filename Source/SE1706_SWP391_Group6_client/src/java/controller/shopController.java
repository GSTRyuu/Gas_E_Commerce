/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.News;
import model.Product;

/**
 *
 * @author Admin
 */
public class shopController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product p = new Product();
        Category c = new Category();
        List<String> mnList = p.getListProductManufacturer();
        List<Product> pList = p.getListProduct();
        String mess = "Home - Shop";
        if (request.getParameter("categoryid") != null) {
            int categoryid = Integer.parseInt(request.getParameter("categoryid"));
            pList = p.getListProductByCategory(categoryid);
            request.setAttribute("categoryid", categoryid);
            c = c.getCategoryByID(categoryid);
            mess = "Category: " + c.getCname();
        }
        if (request.getParameter("manufacturer") != null) {
            pList = p.getListProductByManufacturer(request.getParameter("manufacturer"));
            request.setAttribute("categoryid", request.getParameter("manufacturer"));
            mess = "Manufacturer: " + request.getParameter("manufacturer");
        }
        int page = 1;
        if (request.getParameter("page") != null) {
            Integer.parseInt(request.getParameter("page"));
        }
        int count = pList.size();
        request.setAttribute("count", count);
        int numberPage = p.getNumberPage(pList, 9);
        pList = p.getPaging(pList, page, 9);
        List<Category> cList = c.getListCategory();
        News n = new News();
        request.setAttribute("defendNews", n.getListNews());
        request.setAttribute("p", p);
        request.setAttribute("pList", pList);
        request.setAttribute("cList", cList);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("page", page);
        request.setAttribute("mnList", mnList);
        request.setAttribute("mess", mess);
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product p = new Product();
        List<String> mnList = p.getListProductManufacturer();
        List<Product> prList = p.getListProductByView();
        List<Product> pList = p.getListProduct();
        if (request.getParameter("view") != null && request.getParameter("search") == null) {
            switch (request.getParameter("view")) {
                case "view":
                    pList = p.getListProductByView();
                    break;
                case "sold":
                    pList = p.getListProductBySold();
                    break;
                case "price":
                    pList = p.getListProductBySellPrice();
                    break;
            }
            request.setAttribute("view", request.getParameter("view"));
        }
        if (request.getParameter("categoryid") != null) {
            int categoryid = Integer.parseInt(request.getParameter("category"));
            pList = p.getListProductByCategory(pList, categoryid);
            request.setAttribute("categoryid", categoryid);
        }
        if (request.getParameter("manufacturer") != null) {
            String[] manu = request.getParameterValues("manufacturer");
            if (manu.length != 0) {
                pList = p.getListProductByManufacturer(pList, manu);
                request.setAttribute("manufacturer", manu);
            }
        }
        if (request.getParameter("name") != null) {
            String name = request.getParameter("name");
            pList = p.getProductSearch(pList, name);
            request.setAttribute("name", name);
        }
        if (pList == null) {
            request.setAttribute("mess", "Can't found product");
        }
        if (request.getParameter("minPrice") != null && request.getParameter("maxPrice") != null) {
            int minPrice = Integer.parseInt(request.getParameter("minPrice"));
            int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
            pList = p.getListProductInPriceRange(pList, minPrice, maxPrice);
            request.setAttribute("minPrice", minPrice);
            request.setAttribute("maxPrice", maxPrice);
        }
        Category c = new Category();
        List<Category> cList = c.getListCategory();
        int count = pList.size();
        request.setAttribute("count", count);
        int page = Integer.parseInt(request.getParameter("page"));
        int numberPage = p.getNumberPage(pList, 9);
        pList = p.getPaging(pList, page, 9);
        News n = new News();
        request.setAttribute("mnList", mnList);
        request.setAttribute("pList", pList);
        request.setAttribute("prList", prList);
        request.setAttribute("viewGas", p.getListProductByView());
        request.setAttribute("soldGas", p.getListProductBySold());
        request.setAttribute("sellPriceGas", p.getListProductBySellPrice());
        request.setAttribute("search", request.getParameter("search"));
        request.setAttribute("defendNews", n.getListNews());
        request.setAttribute("cList", cList);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("page", page);
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
