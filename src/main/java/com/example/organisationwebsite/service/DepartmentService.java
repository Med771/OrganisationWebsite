package com.example.organisationwebsite.service;

import com.example.organisationwebsite.module.Employee;

import java.util.HashMap;
import java.util.List;

public sealed interface DepartmentService permits DepartmentServiceImpl{
    Employee findMaxSalaryByDepartment(int departmentId);
    Employee findMinSalaryByDepartment(int departmentId);
    List<Employee> returnEmployeesByDepartment(int departmentId);
    double returnEmployeesSalarySumByDepartmentId(int departmentId);
    HashMap<Integer, List<Employee>> returnAllEmployees();
}
