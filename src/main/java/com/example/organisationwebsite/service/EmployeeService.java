package com.example.organisationwebsite.service;

import com.example.organisationwebsite.module.Employee;

import com.example.organisationwebsite.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Add Employee
    public Employee addEmployee(String firstName, String lastName, int departmentId, double salary) {
        Employee employee = new Employee(firstName, lastName, departmentId, salary);

        employeeRepository.addEmployee(employee);

        return employee;
    }

    // Remove Employee
    public Employee removeEmployee(String firstName, String lastName, int departmentId, double salary) {
        Employee employee = new Employee(firstName, lastName, departmentId, salary);

        employeeRepository.removeEmployee(employee);

        return employee;
    }

    // Find Employee
    public boolean findEmployee(String firstName, String lastName, int departmentId, double salary) {
        Employee employee = new Employee(firstName, lastName, departmentId, salary);

        List<Employee> employees = employeeRepository.getAllEmployees();

        for (Employee emp : employees) {
            if (emp.equals(employee)) {
                return true;
            }
        }

        return false;
    }

    // Get Employee
    public List<Employee> getEmployees() {
        return employeeRepository.getAllEmployees();
    }
}