package com.baitap02.login.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public Connection getConnection() {
        try {

            String url = "jdbc:sqlserver://localhost:1433;databaseName=WebLoginDB;encrypt=true;trustServerCertificate=true;";
            String user = "sa";
            String password = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }
        return null;
    }
}