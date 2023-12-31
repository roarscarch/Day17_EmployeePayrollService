package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class EmpPayrollService {
    private ArrayList<Employee> employees;

    public EmpPayrollService() {
        this.employees = new ArrayList<>();
        this.connectDatabase();
    }

    public void connectDatabase() {
        DBOperations.getConnection();
    }

    public ArrayList<Employee> getEmployeesFromDB() {
        return DBOperations.readEmployees();
    }

    public void updateSalaryInDB(double salary, String name) {
        DBOperations.updateSalary(salary, name);
    }

    public ArrayList<Employee> getInDateRange(String start_date, String end_date) {
        return DBOperations.getInDataRange(start_date, end_date);
    }

    public ArrayList<String> getQueryDataFromDB(String query) {
        return DBOperations.getData(query);
    }

    public ArrayList<String> getStatsByGenderFromDB() {
        return DBOperations.getStatsByGender();
    }

    public void removeEmployee(String name) {
        DBOperations.removeEmployee(name);
    }

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

        DBOperations.addEmployee(employee);
    }

    @Override
    public String toString() {
        String data = "";
        for (Employee employee : this.employees) {
            data += employee.toString() + "\n";
        }
        return data;
    }
}
