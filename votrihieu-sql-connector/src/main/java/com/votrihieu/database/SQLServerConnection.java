package com.votrihieu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

    // Cấu hình thông tin kết nối
    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=LapTrinhWeb;encrypt=true;trustServerCertificate=true;";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";

    // Phương thức để lấy kết nối
    public static Connection getConnection() throws SQLException {
        try {
            // Nạp driver (không bắt buộc từ JDBC 4.0 trở đi nhưng thêm vào cho rõ ràng)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found.", e);
        }
        
        // Trả về đối tượng Connection
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}