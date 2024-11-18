package com.example.organisationwebsite.service;

import com.example.organisationwebsite.module.Employee;

import com.example.organisationwebsite.repository.EmployeeRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public final class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepositoryImpl employeeRepositoryImpl;

    public EmployeeServiceImpl(EmployeeRepositoryImpl employeeRepositoryImpl) {
        this.employeeRepositoryImpl = employeeRepositoryImpl;
    }

    // Add Employee
    public Employee addEmployee(String firstName, String lastName, int departmentId, double salary) {
        Employee employee = new Employee(firstName, lastName, departmentId, salary);

        employeeRepositoryImpl.addEmployee(employee);

        return employee;
    }

    // Remove Employee
    public Employee removeEmployee(String firstName, String lastName, int departmentId, double salary) {
        Employee employee = new Employee(firstName, lastName, departmentId, salary);

        employeeRepositoryImpl.removeEmployee(employee);

        return employee;
    }

    // Find Employee
    public boolean findEmployee(String firstName, String lastName, int departmentId, double salary) {
        Employee employee = new Employee(firstName, lastName, departmentId, salary);

        List<Employee> employees = employeeRepositoryImpl.getAllEmployees();

        for (Employee emp : employees) {
            if (emp.equals(employee)) {
                return true;
            }
        }

        return false;
    }

    // Get Employee
    public List<Employee> getAllEmployees() {
        return employeeRepositoryImpl.getAllEmployees();
    }

    public HashMap<Integer, List<Employee>> getEmployees() {
        return employeeRepositoryImpl.getDepartmentEmployees();
    }
}