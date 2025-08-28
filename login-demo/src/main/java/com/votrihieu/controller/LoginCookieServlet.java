package com.votrihieu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/loginCookie")
public class LoginCookieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if ("votrihieu".equals(user) && "23110219".equals(pass)) {
            // Tạo cookie
            Cookie userNameCookie = new Cookie("user", user);
            userNameCookie.setMaxAge(60*60*24); // Cookie tồn tại trong 1 ngày
            response.addCookie(userNameCookie);

            // Tạo trang chào mừng động
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Welcome</title></head>");
            out.println("<body>");
            out.println("<h1>Chào mừng, " + user + "!</h1>");
            out.println("<p>Bạn đã đăng nhập thành công bằng cookie.</p>");
            out.println("</body>");
            out.println("</html>");
            
        } else {
            // Nếu đăng nhập thất bại, hiển thị thông báo lỗi và link quay lại
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Login Failed</title></head>");
            out.println("<body>");
            out.println("<h3>Username hoặc password không đúng!</h3>");
            out.println("<a href='loginCookie.html'>Thử lại</a>");
            out.println("</body>");
            out.println("</html>");
        }
        out.close();
    }
}