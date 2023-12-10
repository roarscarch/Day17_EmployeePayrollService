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


        //updateData(connection);

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter employee name: ");
//        String employeeName = sc.nextLine();
//        sc.close();
//
//
//        List<Double> salaries = retrieveDataByName(connection, employeeName);
//
//        if (!salaries.isEmpty()) {
//            System.out.println("Salaries of " + employeeName + ": " + salaries);
//        } else {
//            System.out.println("No employee found with the name: " + employeeName);
//        }

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter start date (yyyy-mm-dd): ");
//        String startDate = sc.nextLine();
//        System.out.println("Enter end date (yyyy-mm-dd): ");
//        String endDate = sc.nextLine();
//        sc.close();
//
//
//        List<String> names = particularDateRange(connection, startDate, endDate);
//
//        if (!names.isEmpty()) {
//            System.out.println("Names within the date range: " + names);
//        } else {
//            System.out.println("No records found within the specified date range.");
//        }

        analyzeByGender(connection);

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

    public static List<Double> retrieveDataByName(Connection connection, String employeeName) throws SQLException {
        List<Double> salaries = new ArrayList<>();
        String sqlQuery = "SELECT salary FROM employee_payroll WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, employeeName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double salary = resultSet.getDouble("salary");
                salaries.add(salary);
            }
        }

        return salaries;
    }

    public static List<String> particularDateRange(Connection connection, String startDate, String endDate) throws SQLException {
        List<String> names = new ArrayList<>();
        String query = "SELECT * FROM employee_payroll WHERE startDate BETWEEN ? AND ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, startDate);
        preparedStatement.setString(2, endDate);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(" ");
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            String name = resultSet.getString("name");
            names.add(name);
        }

        return names;
    }

    public static void analyzeByGender(Connection connection) throws SQLException {
        String query = "SELECT gender, SUM(salary) AS total_salary, AVG(salary) AS average_salary, " +
                "MIN(salary) AS min_salary, MAX(salary) AS max_salary, COUNT(*) AS employee_count " +
                "FROM employee_payroll GROUP BY gender";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double totalSalary = resultSet.getDouble("total_salary");
                double averageSalary = resultSet.getDouble("average_salary");
                double minSalary = resultSet.getDouble("min_salary");
                double maxSalary = resultSet.getDouble("max_salary");
                int employeeCount = resultSet.getInt("employee_count");

                System.out.println("Gender: " + gender);
                System.out.println("Total Salary: " + totalSalary);
                System.out.println("Average Salary: " + averageSalary);
                System.out.println("Min Salary: " + minSalary);
                System.out.println("Max Salary: " + maxSalary);
                System.out.println("Number of Employees: " + employeeCount);
                System.out.println();
            }
        }
    }


}
