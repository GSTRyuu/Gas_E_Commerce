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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author fpt
 */
public class signUpController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String mobile = req.getParameter("mobile");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");
        boolean checkMobile = true;
        boolean checkPass = true;
        User u = new User();
        for (int i = 0; i < mobile.length(); i++) {
            if (!Character.isDigit(mobile.charAt(i)) || mobile.length() > 11 || mobile.length() < 10) {
                checkMobile = false;
            }
        }
        if (!pass.equals(repass)) {
            checkPass = false;
        }

        if (!checkMobile) {
            req.setAttribute("mess", "Mobile phone is not valid");
            req.getRequestDispatcher("signUp.jsp").forward(req, resp);
        } else if ((pass.length() < 6) || !isIncludedDigits(pass) || !isIncludedLetters(pass) || !isIncludedSpecialChars(pass)) {
            req.setAttribute("mess", "Password must contain at least 6 included digit, characters and special characters");
            req.getRequestDispatcher("signUp.jsp").forward(req, resp);
        } else if (!checkPass) {
            req.setAttribute("mess", "Re-password is not the same as password");
            req.getRequestDispatcher("signUp.jsp").forward(req, resp);
        } else {
            if (u.checkExistMobile(mobile)) {
                req.setAttribute("mess", "Account already exist");
                req.getRequestDispatcher("signUp.jsp").forward(req, resp);
            } else {
                try {
                    u.signUp(fname, lname, "", mobile, u.getEncryptedPassword(pass), dateFormat.format(date));
                    HttpSession session = req.getSession();
                    User n = u.getLogin(mobile, u.getEncryptedPassword(pass));
                    String ten = n.getFirstName() + " " + n.getLastName();
                    u.updateLastLogin(dateFormat.format(date), n.getId());
                    u.updateLastLogOut(null, n.getId());
                    session.setAttribute("acc", u.getUserById(n.getId()));
                    session.setAttribute("ten", ten);
                    session.setAttribute("pass", pass);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(signUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
                resp.sendRedirect("home");
            }
        }
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
