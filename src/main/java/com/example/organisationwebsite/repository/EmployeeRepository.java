package com.example.organisationwebsite.repository;

import com.example.organisationwebsite.module.Employee;

import java.util.List;

public sealed interface EmployeeRepository permits EmployeeRepositoryImpl {
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
    void removeEmployee(Employee employee);
    void createDepartmentEmployees(int departmentId);
}
