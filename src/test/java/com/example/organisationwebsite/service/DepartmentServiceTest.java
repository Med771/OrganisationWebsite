package com.example.organisationwebsite.service;

import com.example.organisationwebsite.exception.department.DepartmentNotFoundException;
import com.example.organisationwebsite.module.Employee;
import com.example.organisationwebsite.repository.EmployeeRepositoryImpl;

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
class DepartmentServiceTest {
    @Mock
    private EmployeeRepositoryImpl employeeRepositoryMock;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Employee testEmployee1;
    private Employee testEmployee2;
    private HashMap<Integer, List<Employee>> departmentEmployees;

    @BeforeEach
    void setUp() {
        testEmployee1 = new Employee("John", "Doe", 1, 5000);
        testEmployee2 = new Employee("Jane", "Doe", 1, 7000);

        departmentEmployees = new HashMap<>();
        departmentEmployees.put(1, List.of(testEmployee1, testEmployee2));
    }

    // Tests for findMaxSalaryByDepartment
    @Test
    void shouldReturnEmployeeWithMaxSalary() {
        // Arrange
        when(employeeRepositoryMock.getDepartmentEmployees()).thenReturn(departmentEmployees);

        // Act
        Employee result = departmentService.findMaxSalaryByDepartment(1);

        // Assert
        assertEquals(testEmployee2, result);
        verify(employeeRepositoryMock, times(1)).getDepartmentEmployees();
    }

    @Test
    void shouldThrowDepartmentNotFoundExceptionInFindMaxSalary() {
        // Arrange
        departmentEmployees.put(1, List.of());
        when(employeeRepositoryMock.getDepartmentEmployees()).thenReturn(departmentEmployees);

        // Act & Assert
        assertThrows(DepartmentNotFoundException.class, () ->
                departmentService.findMaxSalaryByDepartment(1)
        );
    }

    // Tests for findMinSalaryByDepartment
    @Test
    void shouldReturnEmployeeWithMinSalaryInFindMinSalary() {
        // Arrange
        when(employeeRepositoryMock.getDepartmentEmployees()).thenReturn(departmentEmployees);

        // Act
        Employee result = departmentService.findMinSalaryByDepartment(1);

        // Assert
        assertEquals(testEmployee1, result);
        verify(employeeRepositoryMock, times(1)).getDepartmentEmployees();
    }

    @Test
    void shouldThrowDepartmentNotFoundExceptionInFindMinSalary() {
        // Arrange
        departmentEmployees.put(1, List.of());
        when(employeeRepositoryMock.getDepartmentEmployees()).thenReturn(departmentEmployees);

        // Act & Assert
        assertThrows(DepartmentNotFoundException.class, () ->
                departmentService.findMinSalaryByDepartment(1)
        );
    }

    // Tests for returnEmployeesByDepartment
    @Test
    void shouldReturnListOfEmployees() {
        // Arrange
        when(employeeRepositoryMock.getDepartmentEmployees()).thenReturn(departmentEmployees);

        // Act
        List<Employee> result = departmentService.returnEmployeesByDepartment(1);

        // Assert
        assertEquals(departmentEmployees.get(1), result);
        verify(employeeRepositoryMock, times(1)).getDepartmentEmployees();
    }

    @Test
    void shouldThrowDepartmentNotFoundExceptionInReturnEmployeesByDepartment() {
        // Arrange
        departmentEmployees.remove(1);
        when(employeeRepositoryMock.getDepartmentEmployees()).thenReturn(departmentEmployees);

        // Act & Assert
        assertThrows(DepartmentNotFoundException.class, () ->
                departmentService.returnEmployeesByDepartment(1)
        );
    }

    // Tests for returnEmployeesSalarySumByDepartmentId
    @Test
    void shouldReturnSumOfSalaries() {
        // Arrange
        when(employeeRepositoryMock.getDepartmentEmployees()).thenReturn(departmentEmployees);

        // Act
        double result = departmentService.returnEmployeesSalarySumByDepartmentId(1);

        // Assert
        assertEquals(12000, result);
        verify(employeeRepositoryMock, times(1)).getDepartmentEmployees();
    }

    @Test
    void shouldThrowDepartmentNotFoundExceptionInReturnEmployeesSalarySumByDepartmentId() {
        // Arrange
        departmentEmployees.remove(1);
        when(employeeRepositoryMock.getDepartmentEmployees()).thenReturn(departmentEmployees);

        // Act & Assert
        assertThrows(DepartmentNotFoundException.class, () ->
                departmentService.returnEmployeesSalarySumByDepartmentId(1)
        );
    }

    // Tests for returnAllEmployees
    @Test
    void returnAllEmployees_shouldReturnAllDepartmentEmployees() {
        // Arrange
        when(employeeRepositoryMock.getDepartmentEmployees()).thenReturn(departmentEmployees);

        // Act
        HashMap<Integer, List<Employee>> result = departmentService.returnAllEmployees();

        // Assert
        assertEquals(departmentEmployees, result);
        verify(employeeRepositoryMock, times(1)).getDepartmentEmployees();
    }
}