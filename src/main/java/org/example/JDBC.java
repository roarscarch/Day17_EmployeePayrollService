package org.example;

import java.sql.*;


public class JDBC {
    public Connection getConnection()throws SQLException {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "aNUR@9262";
        return DriverManager.getConnection(url,user,password);
    }

}