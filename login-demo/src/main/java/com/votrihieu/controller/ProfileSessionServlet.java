package com.votrihieu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profileSession")
public class ProfileSessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false); // false: không tạo session mới nếu chưa có

        if (session != null && session.getAttribute("user") != null) {
            String userName = (String) session.getAttribute("user");
            
            out.println("<!DOCTYPE html><html><head><title>Profile</title></head><body>");
            out.println("<h1>Xin chào, " + userName + "!</h1>");
            out.println("<p>Đây là trang cá nhân của bạn.</p>");
            out.println("<a href='logout'>Đăng xuất</a>");
            out.println("</body></html>");
        } else {
            // Nếu chưa đăng nhập, chuyển về trang login
            response.sendRedirect("loginSession.html");
        }
        out.close();
    }
}