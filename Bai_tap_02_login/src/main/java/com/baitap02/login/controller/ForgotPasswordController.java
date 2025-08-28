package com.baitap02.login.controller;

import java.io.IOException;
import java.util.UUID;
import com.baitap02.login.model.User;
import com.baitap02.login.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.findByEmail(email);

        if (user == null) {
            req.setAttribute("error", "Không tìm thấy tài khoản nào với email này.");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
            return;
        }

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        // **QUAN TRỌNG: GỬI EMAIL**
        // Trong một ứng dụng thực tế, bạn sẽ gửi email ở đây.
        // Ví dụ: emailService.send(email, "Reset Password", "Link: .../reset-password?token=" + token);
        
        // **GIẢ LẬP:** Vì đây là project demo, chúng ta sẽ hiển thị link trực tiếp.
        String resetLink = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath() + "/reset-password?token=" + token;

        req.setAttribute("message", "Yêu cầu thành công! Vui lòng kiểm tra email của bạn. <br/>"
                + "Link giả lập để reset: <a href='" + resetLink + "'>" + resetLink + "</a>");
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }
}