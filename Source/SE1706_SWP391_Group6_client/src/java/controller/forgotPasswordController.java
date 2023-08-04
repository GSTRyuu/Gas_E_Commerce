
package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import model.User;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author fpt
 */
public class forgotPasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Thông tin tài khoản Twilio
    private static final String ACCOUNT_SID = "AC7866990d1d794b2f714367cfafe849f2";
    private static final String AUTH_TOKEN = "d438763a968fa774a68d32f7a0105d51";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        User u = new User();
        String phoneNumber = request.getParameter("fpass");
        if(!phoneNumber.equals("0329700816")){
            request.setAttribute("mess", "So dien thoai khong ton tai");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        }else{
        // Tạo mã xác thực ngẫu nhiên
        phoneNumber = "+84" + phoneNumber.substring(1);
        String verificationCode = generateVerificationCode();

        // Lưu mã xác thực vào session (hoặc cơ sở dữ liệu)
        request.getSession().setAttribute("verificationCode", verificationCode);

        // Gửi mã xác thực đến số điện thoại
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(phoneNumber), // Số điện thoại nhận tin nhắn
                new PhoneNumber("+12543554681"), // Số điện thoại Twilio
                "Đây là mã code của bạn: " + verificationCode) // Nội dung tin nhắn
                .create();
        session.setAttribute("code", verificationCode);
        session.setAttribute("fid", u.getUserByMobile("0329700816").getId());
        response.sendRedirect("verify.jsp"); // Chuyển hướng đến trang xác thực

        }
        
        
    }

    // Hàm tạo mã xác thực ngẫu nhiên
    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000; // Mã có 4 chữ số
        return String.valueOf(code);
    }

}
