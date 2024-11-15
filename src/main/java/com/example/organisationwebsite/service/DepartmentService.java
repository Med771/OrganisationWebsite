package com.example.organisationwebsite.service;

import com.example.organisationwebsite.module.Employee;
import com.example.organisationwebsite.repository.EmployeeRepository;

import com.example.organisationwebsite.exception.employee.EmployeeIsNullException;
import com.example.organisationwebsite.exception.department.DepartmentNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class DepartmentService {
    // Init service module
    private final EmployeeRepository employeeRepository;

    public DepartmentService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Find methods
    public Employee findMaxSalaryByDepartment(int departmentId) {
        List<Employee> employees = employeeRepository.getDepartmentEmployees().get(departmentId);


        return employees.stream()
                .filter(emp -> emp.departmentId() == departmentId)
                .max(Comparator.comparingDouble(Employee::salary))
                .orElseThrow(EmployeeIsNullException::new);
    }

    public Employee findMinSalaryByDepartment(int departmentId) {
        List<Employee> employees = employeeRepository.getDepartmentEmployees().get(departmentId);

        return employees.stream()
                .filter(emp -> emp.departmentId() == departmentId)
                .min(Comparator.comparingDouble(Employee::salary))
                .orElseThrow(EmployeeIsNullException::new);
    }

    // Return methods

    public List<Employee> returnEmployeesByDepartment(int departmentId) {
        List<Employee> employees = employeeRepository.getDepartmentEmployees().get(departmentId);

        if (employees == null) {
            throw new DepartmentNotFoundException();
        }

        return employees;
    }

    public HashMap<Integer, List<Employee>> returnAllEmployees() {
        return employeeRepository.getDepartmentEmployees();
    }
}
