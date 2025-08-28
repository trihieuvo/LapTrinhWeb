package com.baitap02.login.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/admin/home") // Lắng nghe và xử lý URL /admin/home
public class AdminHomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển tiếp (forward) request đến file home.jsp trong thư mục admin
        req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
    }
}