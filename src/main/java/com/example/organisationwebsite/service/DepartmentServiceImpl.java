package com.example.organisationwebsite.service;

import com.example.organisationwebsite.module.Employee;
import com.example.organisationwebsite.repository.EmployeeRepositoryImpl;

import com.example.organisationwebsite.exception.department.DepartmentNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public final class DepartmentServiceImpl implements DepartmentService {
    // Init service module
    private final EmployeeRepositoryImpl employeeRepository;

    public DepartmentServiceImpl(EmployeeRepositoryImpl employeeRepositoryImpl) {
        this.employeeRepository = employeeRepositoryImpl;
    }

    // Find methods
    public Employee findMaxSalaryByDepartment(int departmentId) {
        List<Employee> employees = employeeRepository.getDepartmentEmployees().get(departmentId);

        if (employees == null || employees.isEmpty()) {
            throw new DepartmentNotFoundException();
        }

        return employees.stream()
                .max(Comparator.comparingDouble(Employee::salary)).get();
    }

    public Employee findMinSalaryByDepartment(int departmentId) {
        List<Employee> employees = employeeRepository.getDepartmentEmployees().get(departmentId);

        if (employees == null || employees.isEmpty()) {
            throw new DepartmentNotFoundException();
        }

        return employees.stream()
                .min(Comparator.comparingDouble(Employee::salary)).get();
    }

    // Return methods
    public List<Employee> returnEmployeesByDepartment(int departmentId) {
        List<Employee> employees = employeeRepository.getDepartmentEmployees().get(departmentId);

        if (employees == null) {
            throw new DepartmentNotFoundException();
        }

        return employees;
    }

    public double returnEmployeesSalarySumByDepartmentId(int departmentId) {
        List<Employee> employees = employeeRepository.getDepartmentEmployees().get(departmentId);

        if (employees == null) {
            throw new DepartmentNotFoundException();
        }

        double sumSalary = 0;

        for (Employee employee : employees) {
            sumSalary += employee.salary();
        }

        return sumSalary;
    }

    public HashMap<Integer, List<Employee>> returnAllEmployees() {
        return employeeRepository.getDepartmentEmployees();
    }
}
