package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnectivity {
    public static void main(String[] args) throws SQLException {

      JDBC jdbc = new JDBC();
      Connection connection = jdbc.getConnection();
      System.out.println("Database connection to payroll_service established.");
    }
}