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
import java.util.ArrayList;
import model.Admin;
import model.Service;
import model.ServiceItem;

/**
 *
 * @author Dell
 */
public class requestController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceItem si = new ServiceItem();
        String act = req.getParameter("act");
        String ofStatus = req.getParameter("ofStatus");
        String sortBy = req.getParameter("sortBy");
        if (act.equals("filter") || act.equals("paging")) {
            int index;
            if (req.getParameter("index") == null || req.getParameter("index").isEmpty()) {
                index = 1;
            } else {
                index = Integer.parseInt(req.getParameter("index"));
                if (index < 1) {
                    index = 1;
                }
                if (index > si.getEndPage(ofStatus, sortBy)) {
                    index = si.getEndPage(ofStatus, sortBy);
                }
            }
            req.setAttribute("ofStatus", ofStatus);
            req.setAttribute("sortBy", sortBy);
            req.setAttribute("currentPage", index);
            req.setAttribute("siList", si.getListRequest(index, ofStatus, sortBy));
            req.setAttribute("stList", si.getListStatus());
            req.setAttribute("endPage", si.getEndPage(ofStatus, sortBy));
            req.getRequestDispatcher("requestList.jsp").forward(req, resp);
        } else if (act.equals("search")) {
            String input = req.getParameter("searchId");
            if (input == null || input.isEmpty()) {
                resp.sendRedirect("request?act=request");
            } else {
                req.setAttribute("searchId", input);
                if (!input.contains("e")) {
                    req.setAttribute("siList", si.searchServiceItem(Integer.parseInt(input)));
                } else {
                    req.setAttribute("siList", new ArrayList<>());
                }
                req.setAttribute("stList", si.getListStatus());
                req.getRequestDispatcher("requestList.jsp").forward(req, resp);
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
        ServiceItem si = new ServiceItem();
        String act = req.getParameter("act");
        if (act.equals("request")) {
            req.setAttribute("ofStatus", "all");
            req.setAttribute("sortBy", "all");
            req.setAttribute("currentPage", 1);
            req.setAttribute("stList", si.getListStatus());
            req.setAttribute("endPage", si.getEndPage("all", "all"));
            req.setAttribute("siList", si.getListRequest(1, "all", "all"));
            req.getRequestDispatcher("requestList.jsp").forward(req, resp);
        } else if (act.equals("detail")) {
            int reqId = Integer.parseInt(req.getParameter("reqId"));
            si.getServiceItemDetail(reqId);
            req.setAttribute("siDetail", si);
            req.getRequestDispatcher("requestDetail.jsp").forward(req, resp);
        } else if (act.equals("approve")) {
            int reqId = Integer.parseInt(req.getParameter("reqId"));
            si.approveRequest(reqId);
            resp.sendRedirect("request?act=request");
        } else if (act.equals("reject")) {
            int reqId = Integer.parseInt(req.getParameter("reqId"));
            si.rejectRequest(reqId);
            resp.sendRedirect("request?act=request");
        } else if (act.equals("complete")) {
            int reqId = Integer.parseInt(req.getParameter("reqId"));
            si.getServiceItemDetail(reqId);
            req.setAttribute("siDetail", si);
            req.getRequestDispatcher("requestDetail.jsp").forward(req, resp);
        }
    }
}
