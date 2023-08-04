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
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;
import model.Service;
import model.User;

/**
 *
 * @author fpt
 */
public class staffController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("acc") == null) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        Admin ch = (Admin) session.getAttribute("acc");
        if (!(ch.getRole().equals("Admin"))) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        Admin a = new Admin();
        String act = req.getParameter("act");
        if (act.equals("filter") || act.equals("paging") || act.equals("updateRole") || act.equals("delete")) {
            String filterBy = req.getParameter("filterBy");
            String n = req.getParameter("index");
            if (act.equals("updateRole")) {
                a.updateRole(Integer.parseInt(req.getParameter("ofCode")), Integer.parseInt(req.getParameter("role")));
            }
            if (act.equals("delete")) {
                a.deleteAdmin(Integer.parseInt(req.getParameter("deleteCode")));
            }
            int index = calPage(n, filterBy);
            req.setAttribute("filterBy", filterBy);
            req.setAttribute("currentPage", index);
            req.setAttribute("aList", a.getListStaff(index, filterBy));
            req.setAttribute("lrole", a.getListRole());
            req.setAttribute("endPage", a.getEndPage(filterBy));
            req.getRequestDispatcher("staffManage.jsp").forward(req, resp);
        } else if (act.equals("add")) {
            req.setAttribute("lrole", a.getListRole());
            req.getRequestDispatcher("staffDetail.jsp").forward(req, resp);
        } else if (act.equals("addDetail")) {
            String fname = req.getParameter("firstName");
            String lname = req.getParameter("lastName");
            int role = Integer.parseInt(req.getParameter("role"));
            String mobile = req.getParameter("mobile");
            String email = req.getParameter("email");
            String pass = req.getParameter("pass");
            String repass = req.getParameter("repass");
            session.setAttribute("fname", fname);
            session.setAttribute("lname", lname);
            session.setAttribute("mobile", mobile);
            session.setAttribute("email", email);
            session.setAttribute("role", a.getRoleById(role));     
            
            if ((mobile.length() > 11) || (mobile.length() < 10)) {
                req.setAttribute("mess", "Mobile phone is not valid");
                req.setAttribute("lrole", a.getListRole());
                req.getRequestDispatcher("staffDetail.jsp").forward(req, resp);
            } else if ((pass.length() < 6) || !isIncludedDigits(pass) || !isIncludedLetters(pass) || !isIncludedSpecialChars(pass)) {
                req.setAttribute("mess", "Password must contain at least 6 included digit, characters and special characters");
                req.setAttribute("lrole", a.getListRole());
                req.getRequestDispatcher("staffDetail.jsp").forward(req, resp);
            } else if (!pass.equals(repass)) {
                req.setAttribute("mess", "Re-password is not the same as password");
                req.setAttribute("lrole", a.getListRole());
                req.getRequestDispatcher("staffDetail.jsp").forward(req, resp);
            } else {
                if (a.checkExistMobile(mobile)) {
                    req.setAttribute("mess", "Account already exist");
                    req.setAttribute("lrole", a.getListRole());
                    req.getRequestDispatcher("staffDetail.jsp").forward(req, resp);
                } else {
                    try {
                        a.addStaff(role, fname, lname, mobile, email, a.getEncryptedPassword(pass));
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(staffController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    session.removeAttribute("fname");
                    session.removeAttribute("lname");
                    session.removeAttribute("mobile");
                    session.removeAttribute("email");
                    session.removeAttribute("role");
                    resp.sendRedirect("staffManage");
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin a = new Admin();
        req.setAttribute("filterBy", "all");
        req.setAttribute("currentPage", 1);
        req.setAttribute("lrole", a.getListRole());

        req.setAttribute("endPage", a.getEndPage("all"));

        req.setAttribute("aList", a.getListStaff(1, "all"));

        req.getRequestDispatcher("staffManage.jsp").forward(req, resp);
    }

    public static int calPage(String n, String filterBy) {
        Admin a = new Admin();
        int index;
        if (n == null || n.isEmpty()) {
            index = 1;
        } else {
            index = Integer.parseInt(n);
            if (index < 1) {
                index = 1;
            }
            if (index > a.getEndPage(filterBy)) {
                index = a.getEndPage(filterBy);
            }
        }
        return index;
    }

    public static boolean isIncludedDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIncludedLetters(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIncludedSpecialChars(String s) {
        String SpeChar = "~`!@#$%^&*()-_+={][}|:;,<>.?/";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (SpeChar.contains(Character.toString(c))) {
                return true;
            }
        }
        return false;
    }
}
