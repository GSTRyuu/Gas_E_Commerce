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
public class userDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("acc");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String old = req.getParameter("old");
        String n = req.getParameter("new");
        String rn = req.getParameter("renew");
        int id = Integer.parseInt(req.getParameter("id"));
        boolean checkMobile = true;
        for (int i = 0; i < mobile.length(); i++) {
            if (mobile.length() > 11 || mobile.length() < 10) {
                checkMobile = false;
            }
        }
        if (!u.isValidEmail(email) && !email.isEmpty()) {
            req.setAttribute("mess", "Email is not valid");
            req.getRequestDispatcher("userDetail.jsp").forward(req, resp);
        } else if (!checkMobile || mobile.isEmpty() || (!mobile.equals(u.getMobile()) && u.checkExistMobile(mobile))) {
            req.setAttribute("mess", "Mobile phone is not valid or already exist");
            req.getRequestDispatcher("userDetail.jsp").forward(req, resp);
        } else if (!(old.isEmpty() && n.isEmpty() && rn.isEmpty())) {
            if (!session.getAttribute("pass").equals(old) || !n.equals(rn) || n.equals(old)) {
                req.setAttribute("mess", "Cannot change password");
                req.getRequestDispatcher("userDetail.jsp").forward(req, resp);
            } else {
                try {
                    u.updateUserDetailAndPass(mobile, fname, lname, email, id, u.getEncryptedPassword(n));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(userDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }

                String ten = u.getUserById(id).getFirstName() + " " + u.getUserById(id).getLastName();
                session.setAttribute("acc", u.getUserById(id));
                session.setAttribute("ten", ten);
                session.setAttribute("pass", n);
                req.setAttribute("mess", "Update detail and password complete");
                req.getRequestDispatcher("userDetail.jsp").forward(req, resp);

            }
        } else {
            u.updateUserDetail(mobile, fname, lname, email, id);
            String ten = u.getUserById(id).getFirstName() + " " + u.getUserById(id).getLastName();
            session.setAttribute("acc", u.getUserById(id));
            session.setAttribute("ten", ten);

            req.setAttribute("mess", "Update detail complete");
            req.getRequestDispatcher("userDetail.jsp").forward(req, resp);
        }

    }

}

