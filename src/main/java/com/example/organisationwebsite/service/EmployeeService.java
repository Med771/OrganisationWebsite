package com.example.organisationwebsite.service;

import com.example.organisationwebsite.module.Employee;

import java.util.HashMap;
import java.util.List;

public sealed interface EmployeeService permits EmployeeServiceImpl {
    Employee addEmployee(String firstName, String lastName, int departmentId, double salary);
    Employee removeEmployee(String firstName, String lastName, int departmentId, double salary);
    boolean findEmployee(String firstName, String lastName, int departmentId, double salary);
    List<Employee> getAllEmployees();
    HashMap<Integer, List<Employee>> getEmployees();
}
