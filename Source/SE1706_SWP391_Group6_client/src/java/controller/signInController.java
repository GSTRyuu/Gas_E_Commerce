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
public class signInController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobile = req.getParameter("mobile");
        String pass = req.getParameter("pass");
        User u = new User();

        if (!u.checkExistMobile(mobile)) {
            req.setAttribute("mess", "Sai so dien thoai");
            req.getRequestDispatcher("signIn.jsp").forward(req, resp);

        } else {
            try {
                if (!u.verifyPassword(pass, u.getUserByMobile(mobile).getPasswordHash())) {
                    req.setAttribute("mess", "Sai mat khau");
                    req.getRequestDispatcher("signIn.jsp").forward(req, resp);
                } else {
                    HttpSession session = req.getSession();
                    User n = u.getLogin(mobile, u.getEncryptedPassword(pass));
                    String ten = n.getFirstName() + " " + n.getLastName();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    u.updateLastLogin(dateFormat.format(date), n.getId());
                    u.updateLastLogOut(null, n.getId());
                    session.setAttribute("acc", u.getUserById(n.getId()));
                    session.setAttribute("ten", ten);
                    session.setAttribute("pass", pass);
                    
                    
//            req.getRequestDispatcher("home.jsp").forward(req, resp);
                    resp.sendRedirect("home");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(signInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
