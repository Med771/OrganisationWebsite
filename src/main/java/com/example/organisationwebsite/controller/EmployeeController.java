package com.example.organisationwebsite.controller;

import com.example.organisationwebsite.module.Employee;
import com.example.organisationwebsite.service.EmployeeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    // Init service module
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get requests
    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("departmentId") int departmentId,
                                @RequestParam("salary") double salary) {
        return employeeService.addEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("departmentId") int departmentId,
                                   @RequestParam("salary") double salary) {
        return employeeService.removeEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping(path = "/find")
    public boolean findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("departmentId") int departmentId,
                                 @RequestParam("salary") double salary) {
         return employeeService.findEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping(path = "/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }
}
