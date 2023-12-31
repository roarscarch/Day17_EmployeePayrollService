package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeConsole {
    private ArrayList<Employee> employees;
    private String filePath;

    public EmployeeConsole() {
        this.employees = new ArrayList<>();
        // connecting to the database
        this.connectDatabase();
    }

    // employee payroll constructor with file I/O
    public EmployeeConsole(String filePath) {
        this.filePath = filePath;
        FileIo.createFile(filePath);
    }

    // DATABASE FUNCTIONS

    // connecting to the database
    public void connectDatabase() {
        DatabaseOperations.getConnection();
    }

    // method to get all employee data from database
    public ArrayList<Employee> getEmployeesFromDB() {
        return DatabaseOperations.readEmployees();
    }

    // method to update salary in the database
    public void updateSalaryInDB(double salary, String name) {
        DatabaseOperations.updateSalary(salary, name);
    }

    // method to get employees between date range
    public ArrayList<Employee> getInDateRange(String start_date, String end_date) {
        return DatabaseOperations.getInDataRange(start_date, end_date);
    }

    // method to allow custom query execution
    public ArrayList<String> getQueryDataFromDB(String query) {
        return DatabaseOperations.getData(query);
    }

    // method to get salary stats by gender
    public ArrayList<String> getStatsByGenderFromDB() {
        return DatabaseOperations.getStatsByGender();
    }

    // method to remove an employee from the database
    public void removeEmployee(String name) {
        DatabaseOperations.removeEmployee(name);
    }

    // FILE IO FUNCTIONS

    public String getFilePath() {
        return this.filePath;
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }

    // method to add a new employee to the file
    public void addEmployeeToFile(Employee employee) {
        FileIo.writeToFile(filePath, employee.toCSVString());
    }

    // method to count the number of employees in the file
    public int countEmployeesInFile() {
        return FileIo.countLines(this.filePath);
    }

    // method to add an employee from the console to the employees list
    public void addEmployeeConsole(Scanner inputReader) {
        System.out.print("Enter employee Name: ");
        String name = inputReader.nextLine();

        System.out.print("Enter start date (YYYY-MM-DD): ");
        String start_date = inputReader.nextLine();

        System.out.print("Enter employee gender: ");
        String gender = inputReader.nextLine();

        System.out.print("Enter employee phone number: ");
        String phone = inputReader.nextLine();

        System.out.print("Enter employee address: ");
        String address = inputReader.nextLine();

        System.out.print("Enter employee Salary: ");
        double salary = inputReader.nextDouble();
        inputReader.nextLine();

        System.out.print("Enter employee department: ");
        String department = inputReader.nextLine();

        Employee employee = new Employee(name, start_date, gender, phone, address, salary, department);

        DatabaseOperations.addEmployee(employee);
    }

    // method to print all employees in the employees list
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (Employee employee : this.employees) {
            data.append(employee.toString()).append("\n");
        }
        return data.toString();
    }
}
