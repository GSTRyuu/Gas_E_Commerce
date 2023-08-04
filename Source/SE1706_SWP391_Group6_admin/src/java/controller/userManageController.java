/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.staffController.calPage;
import static controller.staffController.isIncludedDigits;
import static controller.staffController.isIncludedLetters;
import static controller.staffController.isIncludedSpecialChars;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;
import model.User;

/**
 *
 * @author fpt
 */
public class userManageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User u = new User();
        String act = req.getParameter("act");
        String n = req.getParameter("index");
        if (act.equals("delete")) {
            u.deleteUser(Integer.parseInt(req.getParameter("deleteCode")));
        }
        int index = calPage(n);
        req.setAttribute("currentPage", index);
        req.setAttribute("aList", u.getListUser(index));
        req.setAttribute("endPage", u.getEndPage());
        req.getRequestDispatcher("userManage.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("acc") == null) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        Admin ch = (Admin) session.getAttribute("acc");
        if (!(ch.getRole().equals("Admin"))) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }

        User u = new User();

        req.setAttribute("currentPage", 1);

        req.setAttribute("endPage", u.getEndPage());

        req.setAttribute("aList", u.getListUser(1));

        req.getRequestDispatcher("userManage.jsp").forward(req, resp);

    }

    public static int calPage(String n) {

        User u = new User();
        int index;
        if (n == null || n.isEmpty()) {
            index = 1;
        } else {
            index = Integer.parseInt(n);
            if (index < 1) {
                index = 1;
            }
            if (index > u.getEndPage()) {
                index = u.getEndPage();
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
