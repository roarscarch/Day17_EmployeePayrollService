package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseConnectivity {
    public static void main(String[] args) throws SQLException {
        JDBC jdbc = new JDBC();
        Connection connection = jdbc.getConnection();
        System.out.println("Database connection to payroll_service established.");

        // Example: Call the readEmployee method using the established connection
        DataBaseConnectivity dbConnectivity = new DataBaseConnectivity();
        dbConnectivity.readEmployee(connection);
    }

    public void readEmployee(Connection connection) {
        List<Employee> employeeList = new ArrayList<>();
        String sqlQuery = "select * from employee_payroll";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                Date startDate = resultSet.getDate("startDate");

                Employee employee = new Employee(id, name,salary, startDate);
                employeeList.add(employee);

                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", StartDate: " + startDate);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
