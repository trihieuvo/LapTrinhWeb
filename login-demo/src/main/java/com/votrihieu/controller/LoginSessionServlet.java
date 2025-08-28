package com.votrihieu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginSession")
public class LoginSessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if ("votrihieu".equals(user) && "23110219".equals(pass)) {
            // Tạo session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            // Chuyển hướng đến servlet profile
            response.sendRedirect("profileSession");
        } else {
            // Nếu sai, trả về trang đăng nhập với thông báo lỗi
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<p>Sai username hoặc password!</p>");
            request.getRequestDispatcher("loginSession.html").include(request, response);
        }
    }
}