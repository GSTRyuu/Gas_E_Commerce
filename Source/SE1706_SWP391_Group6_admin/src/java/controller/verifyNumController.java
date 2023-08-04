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
import model.Admin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author fpt
 */
public class verifyNumController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // Thông tin tài khoản Twilio
    private static final String ACCOUNT_SID = "AC7866990d1d794b2f714367cfafe849f2";
    private static final String AUTH_TOKEN = "d438763a968fa774a68d32f7a0105d51";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin ad = new Admin();
        boolean check;
        HttpSession session = request.getSession();
        String phoneNumber = request.getParameter("mobile");
        if (ad.getAdminByMobile(phoneNumber) == null) {
            check = false;
        } else {
            phoneNumber = "+84" + phoneNumber.substring(1);
            // Tạo mã xác thực ngẫu nhiên
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
            check = true;
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        if(check){
            response.getWriter().write("Code đã được gửi");
        }else{
            response.getWriter().write("Số điện thoại không tồn tại");
        }
        

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin ad = new Admin();
        String verify = req.getParameter("verify");
        String mobile = req.getParameter("mobile");
        HttpSession session = req.getSession();
        String code = session.getAttribute("code").toString();
        if (!code.equals(verify)) {
            req.setAttribute("mess", "Code khong hop le");
            req.getRequestDispatcher("forgotpass.jsp").forward(req, resp);
        } else {
            session.setAttribute("id", ad.getAdminByMobile(mobile).getId());
            resp.sendRedirect("changePassword.jsp");

        }
    }

    // Hàm tạo mã xác thực ngẫu nhiên
    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000; // Mã có 4 chữ số
        return String.valueOf(code);
    }

}
