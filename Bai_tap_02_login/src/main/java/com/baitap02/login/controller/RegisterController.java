package com.baitap02.login.controller;


import java.io.IOException;

import com.baitap02.login.service.UserService;
import com.baitap02.login.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");
        String re_password = req.getParameter("re_password");
        String alertMsg = "";

        if (!password.equals(re_password)) {
            alertMsg = "Mật khẩu nhập lại không khớp!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();

        if (service.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        boolean isSuccess = service.register(email, password, username, fullname);

        if (isSuccess) {
            // Có thể thêm 1 thông báo thành công trên trang login
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "Đã có lỗi xảy ra từ hệ thống!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}
