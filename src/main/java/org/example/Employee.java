package org.example;

import java.util.Date;

public class Employee {
    private int id;
    private String name;

    private double salary;
    private Date startDate;

    public Employee(int id, String name, double salary, Date startDate) {
        this.id = id;
        this.name = name;

        this.salary = salary;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", startDate=" + startDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
