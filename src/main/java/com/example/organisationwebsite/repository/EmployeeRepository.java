package com.example.organisationwebsite.repository;

import com.example.organisationwebsite.module.Employee;

import org.springframework.stereotype.Repository;

import com.example.organisationwebsite.exception.employee.EmployeeNotFoundException;
import com.example.organisationwebsite.exception.employee.EmployeeAlreadyAddedException;
import com.example.organisationwebsite.exception.employee.EmployeeStorageIsFullException;
import com.example.organisationwebsite.exception.employee.EmployeeIsNullException;

import com.example.organisationwebsite.exception.department.DepartmentStorageIsFullException;
import com.example.organisationwebsite.exception.department.DepartmentNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public final class EmployeeRepository {
    // Init
    private final int maxSizeMap = 1000;
    private final int maxSizeSet = 1000;
    private final HashMap<Integer, List<Employee>> departmentEmployees = new HashMap<>(maxSizeMap);

    // Get
    public HashMap<Integer, List<Employee>> getDepartmentEmployees() {
        return departmentEmployees;
    }

    // Return List<Employee>
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>(maxSizeSet * maxSizeMap);

        for (List<Employee> list : departmentEmployees.values()) {
            employees.addAll(list);
        }

        return employees;
    }

    // Add Employee
    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new EmployeeIsNullException();
        }

        if (departmentEmployees.size() >= maxSizeMap) {
            throw new EmployeeAlreadyAddedException();
        }

        createDepartmentEmployees(employee.departmentId());

        if (departmentEmployees.get(employee.departmentId()).size() >= maxSizeSet) {
            throw new EmployeeStorageIsFullException();
        }

        if (departmentEmployees.get(employee.departmentId()).contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }

        departmentEmployees.get(employee.departmentId()).add(employee);
    }

    // Remove Employee
    public void removeEmployee(Employee employee) {
        if (employee == null) {
            throw new EmployeeIsNullException();
        }

        if (departmentEmployees.get(employee.departmentId()) == null) {
            throw new DepartmentNotFoundException();
        }

        boolean Removed = departmentEmployees.get(employee.departmentId()).remove(employee);

        if (!Removed) {
            throw new EmployeeNotFoundException();
        }

        if (departmentEmployees.get(employee.departmentId()).isEmpty()) {
            departmentEmployees.remove(employee.departmentId());
        }
    }

    // Create Department
    public void createDepartmentEmployees(int departmentId) {
        if (departmentEmployees.get(departmentId) == null) {
            if (departmentEmployees.size() >= maxSizeMap) {
                throw new DepartmentStorageIsFullException();
            }

            departmentEmployees.put(departmentId, new ArrayList<>(maxSizeSet));
        }
    }
}
