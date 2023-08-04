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

/**
 *
 * @author fpt
 */
public class verifyController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String verify = req.getParameter("verify");
        HttpSession session = req.getSession();
        String code = session.getAttribute("code").toString();
        if(!code.equals(verify)){
        req.setAttribute("mess", "Code khong hop le");
        req.getRequestDispatcher("verify.jsp").forward(req, resp);
        }else{
        resp.sendRedirect("changePassword.jsp");
        
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    
}
