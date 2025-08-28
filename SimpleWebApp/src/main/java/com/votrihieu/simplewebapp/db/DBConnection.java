package com.votrihieu.simplewebapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    // Thay đổi các thông số này cho phù hợp với cấu hình SQL Server của bạn
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=JavaWebDB;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa"; // <-- THAY USERNAME CỦA BẠN
    private static final String PASS = "123"; // <-- THAY PASSWORD CỦA BẠN

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Đăng ký driver
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            // Tạo kết nối
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console để debug
            return null;
        }
        return conn;
    }
}
