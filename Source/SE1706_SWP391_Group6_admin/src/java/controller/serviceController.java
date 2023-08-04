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
import model.Service;

/**
 *
 * @author Dell
 */
public class serviceController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service s = new Service();
        String act = req.getParameter("act");
        String ofType = req.getParameter("ofType");
        String filterBy = req.getParameter("filterBy");
        if (act.equals("filter") || act.equals("paging") || act.equals("updateStatus") || act.equals("delete")) {
            if (act.equals("updateStatus")) {
                s.updateStatus(req.getParameter("ofCode"), req.getParameter("serviceStatus"));
            }
            if (act.equals("delete")) {
                if (!s.deleteService(req.getParameter("deleteCode"))) {
                    req.setAttribute("deny", true);
                } else {
                    req.setAttribute("deny", false);
                }
            }
            int index;
            if (req.getParameter("index") == null || req.getParameter("index").isEmpty()) {
                index = 1;
            } else {
                index = Integer.parseInt(req.getParameter("index"));
                if (index < 1) {
                    index = 1;
                }
                if (index > s.getEndPage(ofType, filterBy)) {
                    index = s.getEndPage(ofType, filterBy);
                }
            }
            req.setAttribute("ofType", ofType);
            req.setAttribute("filterBy", filterBy);
            req.setAttribute("currentPage", index);
            req.setAttribute("sList", s.getListServices(index, filterBy, ofType));
            req.setAttribute("tList", s.getListType());
            req.setAttribute("stList", s.getListStatus());
            req.setAttribute("endPage", s.getEndPage(ofType, filterBy));
            req.getRequestDispatcher("serviceManagement.jsp").forward(req, resp);
        } else if (act.equals("search")) {
            String input = req.getParameter("searchCode");
            req.setAttribute("searchCode", input);
            req.setAttribute("sList", s.searchServices(input));
            req.setAttribute("tList", s.getListType());
            req.setAttribute("stList", s.getListStatus());
            req.getRequestDispatcher("serviceManagement.jsp").forward(req, resp);
        } else if (act.equals("detail")) {
            String detailCode = req.getParameter("detailCode");
            s.getServiceDetail(detailCode);
            req.setAttribute("stList", s.getListStatus());
            req.setAttribute("tList", s.getListType());
            req.setAttribute("sDetail", s);
            req.setAttribute("act", act);
            req.getRequestDispatcher("serviceDetail.jsp").forward(req, resp);
        } else if (act.equals("add")) {
            req.setAttribute("stList", s.getListStatus());
            req.setAttribute("tList", s.getListType());
            req.setAttribute("act", act);
            req.getRequestDispatcher("serviceDetail.jsp").forward(req, resp);
        } else if (act.equals("submitAdd") || act.equals("submitUpdate")) {
            String oldCode = req.getParameter("oldCode");
            String serviceCode = req.getParameter("serviceCode");
            String serviceType = req.getParameter("serviceType");
            if (act.equals("submitAdd")) {
                if (!req.getParameter("newType").equals("")) {
                    serviceType = req.getParameter("newType");
                }
            }
            String serviceName = req.getParameter("serviceName");
            int serviceAmount = 0;
            if (!req.getParameter("serviceAmount").equals("")) {
                serviceAmount = Integer.parseInt(req.getParameter("serviceAmount"));
            }
            double servicePrice = Double.parseDouble(req.getParameter("servicePrice"));
            String serviceContent = req.getParameter("serviceContent");
            String serviceStatus = req.getParameter("serviceStatus");
            if (act.equals("submitAdd")) {
                if (!s.addService(serviceCode, serviceType, serviceName, serviceAmount, servicePrice, serviceStatus, serviceContent)) {
                    req.setAttribute("denyAdd", true);
                    req.setAttribute("stList", s.getListStatus());
                    req.setAttribute("tList", s.getListType());
                    req.setAttribute("act", "add");
                    req.getRequestDispatcher("serviceDetail.jsp").forward(req, resp);
                } else {
                    s.getServiceDetail(serviceCode);
                    req.setAttribute("stList", s.getListStatus());
                    req.setAttribute("tList", s.getListType());
                    req.setAttribute("sDetail", s);
                    req.setAttribute("act", "detail");
                    req.getRequestDispatcher("serviceDetail.jsp").forward(req, resp);
                }
            } else if (act.equals("submitUpdate")) {
                if (!s.updateService(oldCode, serviceCode, serviceType, serviceName, serviceAmount, servicePrice, serviceStatus, serviceContent)) {
                    req.setAttribute("denyUpdate", true);
                    s.getServiceDetail(oldCode);
                    req.setAttribute("stList", s.getListStatus());
                    req.setAttribute("tList", s.getListType());
                    req.setAttribute("sDetail", s);
                    req.setAttribute("act", "detail");
                    req.getRequestDispatcher("serviceDetail.jsp").forward(req, resp);
                } else {
                    s.getServiceDetail(serviceCode);
                    req.setAttribute("stList", s.getListStatus());
                    req.setAttribute("tList", s.getListType());
                    req.setAttribute("sDetail", s);
                    req.setAttribute("act", "detail");
                    req.getRequestDispatcher("serviceDetail.jsp").forward(req, resp);
                }
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
        Service s = new Service();
        req.setAttribute("ofType", "all");
        req.setAttribute("filterBy", "all");
        req.setAttribute("currentPage", 1);
        req.setAttribute("tList", s.getListType());
        req.setAttribute("stList", s.getListStatus());
        req.setAttribute("endPage", s.getEndPage("all", "all"));
        req.setAttribute("sList", s.getListServices(1, "all", "all"));
        req.getRequestDispatcher("serviceManagement.jsp").forward(req, resp);
    }
}
