package com.example.organisationwebsite.controller;

import com.example.organisationwebsite.module.Employee;
import com.example.organisationwebsite.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    // Init service module
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Get methods
    @GetMapping(path = "/max-salary")
    public Employee getMaxSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.findMaxSalaryByDepartment(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee getMinSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.findMinSalaryByDepartment(departmentId);
    }

    @GetMapping(path = "all")
    public Object getAllEmployees(@RequestParam(required = false, defaultValue = "-1") int departmentId) {
        if (departmentId == -1) {
            return departmentService.returnAllEmployees();
        }

        return departmentService.returnEmployeesByDepartment(departmentId);
    }
}
