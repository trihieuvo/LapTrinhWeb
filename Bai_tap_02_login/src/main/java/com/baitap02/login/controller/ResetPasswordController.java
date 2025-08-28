package com.baitap02.login.controller;

import java.io.IOException;
import java.util.Calendar;
import com.baitap02.login.model.User;
import com.baitap02.login.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        User user = userService.getUserByPasswordResetToken(token);

        if (user == null) {
            req.setAttribute("error", "Token không hợp lệ.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra token đã hết hạn chưa
        Calendar cal = Calendar.getInstance();
        if (user.getTokenExpiryDate().before(cal.getTime())) {
            req.setAttribute("error", "Token đã hết hạn.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Token hợp lệ, chuyển đến trang reset
        req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu không khớp.");
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
            return;
        }

        User user = userService.getUserByPasswordResetToken(token);
        if (user == null) {
             req.setAttribute("error", "Token không hợp lệ.");
             req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
             return;
        }

        userService.changeUserPassword(user, password);
        req.setAttribute("message", "Mật khẩu của bạn đã được thay đổi thành công!");
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }
}