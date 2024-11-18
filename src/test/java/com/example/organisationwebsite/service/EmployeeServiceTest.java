package com.example.organisationwebsite.service;

import com.example.organisationwebsite.module.Employee;
import com.example.organisationwebsite.repository.EmployeeRepositoryImpl;
import com.example.organisationwebsite.exception.employee.EmployeeAlreadyAddedException;
import com.example.organisationwebsite.exception.employee.EmployeeIsNullException;
import com.example.organisationwebsite.exception.employee.EmployeeNotFoundException;
import com.example.organisationwebsite.exception.department.DepartmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepositoryImpl employeeRepositoryMock;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        testEmployee = new Employee("John", "Doe", 1, 5000);
    }

    // Test for addEmployee
    @Test
    void shouldAddEmployee() {
        // Act
        Employee result = employeeService.addEmployee("John", "Doe", 1, 5000);

        // Assert
        assertEquals(testEmployee, result);
        verify(employeeRepositoryMock, times(1)).addEmployee(testEmployee);
    }

    // Test for removeEmployee
    @Test
    void shouldRemoveEmployee() {
        // Act
        Employee result = employeeService.removeEmployee("John", "Doe", 1, 5000);

        // Assert
        assertEquals(testEmployee, result);
        verify(employeeRepositoryMock, times(1)).removeEmployee(testEmployee);
    }

    // Test for findEmployee
    @Test
    void shouldReturnTrueWhenEmployeeExists() {
        // Arrange
        when(employeeRepositoryMock.getAllEmployees()).thenReturn(List.of(testEmployee));

        // Act
        boolean result = employeeService.findEmployee("John", "Doe", 1, 5000);

        // Assert
        assertTrue(result);
        verify(employeeRepositoryMock, times(1)).getAllEmployees();
    }

    @Test
    void getAllEmployees_shouldReturnListOfEmployees() {
        // Arrange
        List<Employee> employees = List.of(testEmployee);
        when(employeeRepositoryMock.getAllEmployees()).thenReturn(employees);

        // Act
        List<Employee> result = employeeService.getAllEmployees();

        // Assert
        assertEquals(employees, result);
        verify(employeeRepositoryMock, times(1)).getAllEmployees();
    }

    @Test
    void getEmployees_shouldReturnDepartmentEmployees() {
        // Arrange
        HashMap<Integer, List<Employee>> departmentEmployees = new HashMap<>();
        departmentEmployees.put(1, List.of(testEmployee));
        when(employeeRepositoryMock.getDepartmentEmployees()).thenReturn(departmentEmployees);

        // Act
        HashMap<Integer, List<Employee>> result = employeeService.getEmployees();

        // Assert
        assertEquals(departmentEmployees, result);
        verify(employeeRepositoryMock, times(1)).getDepartmentEmployees();
    }
}