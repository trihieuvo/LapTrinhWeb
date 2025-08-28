package com.votrihieu.simplewebapp.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.votrihieu.simplewebapp.db.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("test".equals(action)) {
            testConnection(request, response);
        } else if ("show".equals(action)) {
            showData(request, response);
        } else {
            // Mặc định chuyển đến trang JSP
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void testConnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                message = "Kết nối đến cơ sở dữ liệu THÀNH CÔNG!";
            } else {
                message = "Kết nối đến cơ sở dữ liệu THẤT BẠI!";
            }
        } catch (SQLException e) {
            message = "Lỗi khi kết nối: " + e.getMessage();
            e.printStackTrace();
        }
        request.setAttribute("testMessage", message);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void showData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> productList = new ArrayList<>();
        String query = "SELECT ProductName, Price FROM Products";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("ProductName");
                double price = rs.getDouble("Price");
                productList.add(String.format("Tên: %s - Giá: %,.0f VNĐ", name, price));
            }

        } catch (Exception e) {
            e.printStackTrace();
            productList.add("Lỗi khi tải dữ liệu: " + e.getMessage());
        }
        request.setAttribute("products", productList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}