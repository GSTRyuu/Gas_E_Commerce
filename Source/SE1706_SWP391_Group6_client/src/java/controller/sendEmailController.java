/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import model.User;

public class sendEmailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy giá trị email từ request
        HttpSession session1 = request.getSession();
        String email = request.getParameter("email");
        User u = new User();
        if(!u.checkExistEmail(email)){
            u.signUp("", "", email, "", "", "GETDATE()");
            session1.setAttribute("functionToast", "showToast('success','Save email successfully.')");
        }
        
        // Cấu hình thông tin email
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "hungnmhe171373@fpt.edu.vn";
        String password = "cvypvoutvvjolota";
        String fromAddress = "hungnmhe171373@fpt.edu.vn";
        String toAddress = email;
        String subject = "Subject of the email";
        String messageContent = "Content of the email";

        // Cấu hình Jakarta Mail
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");

        // Xây dựng đối tượng Session để xác thực và kết nối đến SMTP server
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Thiết lập thông tin người gửi
            message.setFrom(new InternetAddress(fromAddress));

            // Thiết lập thông tin người nhận
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

            // Thiết lập tiêu đề và nội dung email
            message.setSubject(subject);
            message.setText(messageContent);

            // Gửi email
            Transport.send(message);

            // Chuyển hướng người dùng sau khi gửi email thành công
            response.sendRedirect("home");
        } catch (MessagingException mex) {
            // Xử lý lỗi nếu có
            mex.printStackTrace();
            response.sendRedirect("home.jsp");
        }
    }
}
