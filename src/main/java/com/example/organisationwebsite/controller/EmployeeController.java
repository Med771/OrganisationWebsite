package com.example.organisationwebsite.controller;

import com.example.organisationwebsite.module.Employee;
import com.example.organisationwebsite.service.EmployeeServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    // Init service module
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    // Get requests
    @GetMapping(path = "/")
    public HashMap<Integer, List<Employee>> getEmployees() {
        return employeeServiceImpl.getEmployees();
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("departmentId") int departmentId,
                                @RequestParam("salary") double salary) {
        return employeeServiceImpl.addEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("departmentId") int departmentId,
                                   @RequestParam("salary") double salary) {
        return employeeServiceImpl.removeEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping(path = "/find")
    public boolean findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("departmentId") int departmentId,
                                 @RequestParam("salary") double salary) {
         return employeeServiceImpl.findEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping(path = "/all")
    public List<Employee> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }
}
