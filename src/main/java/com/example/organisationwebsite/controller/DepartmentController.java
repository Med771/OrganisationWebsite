package com.example.organisationwebsite.controller;

import com.example.organisationwebsite.module.Employee;
import com.example.organisationwebsite.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    // Init service module
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentServiceImpl) {
        this.departmentService = departmentServiceImpl;
    }

    // Get methods
    @GetMapping(path = "/{id}/employees")
    public List<Employee> getEmployees(@PathVariable int id) {
        return departmentService.returnEmployeesByDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/max")
    public Employee getMaxSalary(@PathVariable int id) {
        return departmentService.findMaxSalaryByDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/min")
    public Employee getMinSalary(@PathVariable int id) {
        return departmentService.findMinSalaryByDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public double getSumSalary(@PathVariable int id) {
        return departmentService.returnEmployeesSalarySumByDepartmentId(id);
    }

    @GetMapping(path = "/employees")
    public HashMap<Integer, List<Employee>> getAllEmployees() {
        return departmentService.returnAllEmployees();
    }
}
