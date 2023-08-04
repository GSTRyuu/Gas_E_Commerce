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
import model.User;

/**
 *
 * @author fpt
 */
public class changePasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = new User();
        String n = req.getParameter("n");
        String rn = req.getParameter("rn");
        String id = req.getParameter("id");
        if ((n.length() < 6) || !isIncludedDigits(n) || !isIncludedLetters(n) || !isIncludedSpecialChars(n)) {
            req.setAttribute("mess", "Password must contain at least 6 included digit, characters and special characters");
            req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
        } else if (!n.equals(rn)) {
            req.setAttribute("mess", "Mat khau moi khong hop le");
            req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
        } else {
            try {
                u.updatePass(u.getEncryptedPassword(n), id);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(changePasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("mess", "Da doi mat khau thanh cong");
            req.getRequestDispatcher("signIn.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
