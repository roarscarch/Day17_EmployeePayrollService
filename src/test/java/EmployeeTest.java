import java.sql.*;
import java.util.ArrayList;
import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// All the test of Employee-Payroll Program
public class EmployeeTest {

    // testing the add method
    @Test
    public void testAddEmployee() {
        Employee employee = new Employee("John Doe", "2023-06-15", "male", "phone", "123 Main St", 50000, "Test");
        int employees_before = DatabaseOperations.readEmployees().size();
        DatabaseOperations.addEmployee(employee);
        ArrayList<Employee> employees = DatabaseOperations.readEmployees();
        int employees_after = employees.size();
        assertEquals(employees_before + 1, employees_after);
        assertTrue(employees.stream().anyMatch(emp -> emp.getName().equals("John Doe")));
    }

    @Test
    public void testRemoveEmployee() {
        Employee employee = new Employee("Jane Doe", "2023-07-20", "female", "phone", "456 Oak St", 50000, "Test");
        DatabaseOperations.addEmployee(employee);
        int employees_before = DatabaseOperations.readEmployees().size();
        DatabaseOperations.removeEmployee("Jane Doe");
        int employees_after = DatabaseOperations.readEmployees().size();
        assertEquals(employees_before, employees_after + 1);
    }

    @Test
    public void testUpdateSalary() {
        Employee employee = new Employee("Jim Smith", "2023-08-10", "male", "phone", "789 Pine St", 50000, "Test");
        DatabaseOperations.addEmployee(employee);
        DatabaseOperations.updateSalary(30000, "Jim Smith");
        String query = "select salary from employee_payroll where name = \"Jim Smith\";";
        double new_salary = -1;
        try (
                Connection connection = DriverManager.getConnection(DatabaseDetails.URL, DatabaseDetails.USER, DatabaseDetails.PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                new_salary = resultSet.getDouble("salary");
            }
            assertEquals(30000, new_salary);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            fail();
        }
    }
}
