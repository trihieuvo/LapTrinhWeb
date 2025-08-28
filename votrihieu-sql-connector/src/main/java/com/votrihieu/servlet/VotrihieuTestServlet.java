package com.votrihieu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.votrihieu.database.SQLServerConnection;

public class VotrihieuTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>SQL Server Connection Test</title></head><body>");
        out.println("<h1>Kiểm tra kết nối đến SQL Server</h1>");

        try (Connection conn = SQLServerConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            out.println("<p>Kết nối đến database thành công!</p>");
            
            String sql = "SELECT @@VERSION";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    out.println("<p>Phiên bản SQL Server:</p>");
                    out.println("<pre>" + rs.getString(1) + "</pre>");
                }
            }

        } catch (Exception e) {
            out.println("<p style='color:red;'>Kết nối thất bại! Lỗi: " + e.getMessage() + "</p>");
            e.printStackTrace(out); // In chi tiết lỗi ra trang web để debug
        }
        
        out.println("</body></html>");
    }
}