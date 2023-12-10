package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DataBaseConnectivity {

    public static void main(String[] args) throws SQLException {
        JDBC jdbc = new JDBC();
        Connection connection = jdbc.getConnection();
        System.out.println("Database connection to payroll_service established.");


        DataBaseConnectivity dbConnectivity = new DataBaseConnectivity();
        dbConnectivity.readEmployee(connection);


        updateData(connection);
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

                Employee employee = new Employee(id, name, salary, startDate);
                employeeList.add(employee);

                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", StartDate: " + startDate);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void updateData(Connection connection) {
        Scanner sc = new Scanner(System.in);
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE employee_payroll SET salary = ? WHERE id = ?;");

            System.out.println("Enter salary to be updated: ");
            double salary = sc.nextDouble();
            System.out.println("Enter at which id you want to update salary: ");
            int id = sc.nextInt();

            preparedStatement.setDouble(1, salary);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {


                System.out.println("Updated Successfully!!!");

            } else {
                System.out.println("No employee found with the provided ID.");
            }
        } catch (SQLException | java.util.InputMismatchException exception) {
            exception.printStackTrace();
        } finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sc.close();
        }
    }
}
