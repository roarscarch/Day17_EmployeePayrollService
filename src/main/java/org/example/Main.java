package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int EXIT_OPTION = 0;
    private static final int PRINT_ALL_OPTION = 1;
    private static final int ADD_EMPLOYEE_OPTION = 2;
    private static final int REMOVE_EMPLOYEE_OPTION = 3;
    private static final int PRINT_IN_DATE_RANGE_OPTION = 4;
    private static final int UPDATE_SALARY_OPTION = 5;
    private static final int VIEW_BY_GENDER_OPTION = 6;

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll Service.");

        EmpPayrollService empPayrollService = new EmpPayrollService();

        try (Scanner inputReader = new Scanner(System.in)) {
            boolean continueLoop = true;
            while (continueLoop) {
                System.out.println("What operation do you want to perform?");
                System.out.println("[1] Print all employees details");
                System.out.println("[2] Add new employee");
                System.out.println("[3] Remove employee");
                System.out.println("[4] Print employees joining in date range");
                System.out.println("[5] Update employee salary");
                System.out.println("[6] View employee details by gender");
                System.out.print("Enter your choice (enter 0 to exit): ");
                int choice = inputReader.nextInt();
                inputReader.nextLine();

                switch (choice) {
                    case EXIT_OPTION:
                        continueLoop = false;
                        break;

                    case PRINT_ALL_OPTION:
                        printEmployees(empPayrollService.getEmployeesFromDB());
                        break;

                    case ADD_EMPLOYEE_OPTION:
                        empPayrollService.addEmployeeConsole(inputReader);
                        break;

                    case REMOVE_EMPLOYEE_OPTION:
                        String empName = readInput(inputReader, "Enter employee name: ");
                        empPayrollService.removeEmployee(empName);
                        break;

                    case PRINT_IN_DATE_RANGE_OPTION:
                        String startDate = readInput(inputReader, "Enter start date (YYYY-MM-DD): ");
                        String endDate = readInput(inputReader, "Enter end date (YYYY-MM-DD): ");
                        printDetailsInRange(empPayrollService.getInDateRange(startDate, endDate));
                        break;

                    case UPDATE_SALARY_OPTION:
                        String name = readInput(inputReader, "Enter employee name: ");
                        double salary = inputReader.nextDouble();
                        inputReader.nextLine();
                        empPayrollService.updateSalaryInDB(salary, name);
                        break;

                    case VIEW_BY_GENDER_OPTION:
                        printSalaryStatsByGender(empPayrollService.getStatsByGenderFromDB());
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        }
    }

    private static void printEmployees(ArrayList<Employee> employees) {
        int i = 1;
        for (Employee employee : employees) {
            System.out.println(i + ")\n" + employee + "\n");
            i++;
        }
    }

    private static void printDetailsInRange(ArrayList<Employee> employees) {
        int i = 1;
        for (Employee employee : employees) {
            System.out.println(i + ")\n" + employee + "\n");
            i++;
        }
    }

    private static void printSalaryStatsByGender(ArrayList<String> salaryStats) {
        for (String str : salaryStats) {
            String[] details = str.split(", ");
            System.out.println("Gender: " + details[0]);
            System.out.println("Total Salary: " + details[1]);
            System.out.println("Minimum Salary: " + details[2]);
            System.out.println("Maximum Salary: " + details[3]);
            System.out.println("Average Salary: " + details[4] + "\n");
        }
    }

    private static String readInput(Scanner inputReader, String prompt) {
        System.out.print(prompt);
        return inputReader.nextLine();
    }
}
