/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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
import model.Admin;

/**
 *
 * @author fpt
 */
public class signInController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String mobile = req.getParameter("mobile");
        String pass = req.getParameter("password");
        String remember = req.getParameter("remember");
        Admin ad = new Admin();

        if (ad.getAdminByMobile(mobile) == null) {
            req.setAttribute("mess", "Sai so dien thoai");
            req.getRequestDispatcher("signIn.jsp").forward(req, resp);

        } else {
            try {
                if (!ad.verifyPassword(pass, ad.getAdminByMobile(mobile).getPasswordHash())) {
                    req.setAttribute("mess", "Sai mat khau");
                    req.getRequestDispatcher("signIn.jsp").forward(req, resp);
                } else {
                    session.setAttribute("acc", ad.getAdminByMobile(mobile));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    ad.updateLastLogin(dateFormat.format(date), ad.getAdminByMobile(mobile).getId());
                    if (remember != null && remember.equals("on")) {
                        // Lưu thông tin đăng nhập vào cookie
                        Cookie u = new Cookie("username", mobile);
                        Cookie p = new Cookie("password", pass);
                        Cookie r = new Cookie("remember", remember);
                        u.setMaxAge(86400); // Thời gian tồn tại của cookie (số giây), ở đây là 1 ngày
                        p.setMaxAge(86400); // Thời gian tồn tại của cookie (số giây), ở đây là 1 ngày
                        r.setMaxAge(86400); // Thời gian tồn tại của cookie (số giây), ở đây là 1 ngày
                        resp.addCookie(u);
                        resp.addCookie(p);
                        resp.addCookie(r);
                    } else {
                        // Xóa cookie nếu có
                        Cookie[] cookies = req.getCookies();
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("username") || cookie.getName().equals("password") || cookie.getName().equals("remember")) {
                                    cookie.setMaxAge(0); // Đặt thời gian tồn tại của cookie là 0 để xóa nó
                                    resp.addCookie(cookie);
                                }
                            }
                        }
                    }
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
