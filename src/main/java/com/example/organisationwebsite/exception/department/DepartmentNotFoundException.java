package com.example.organisationwebsite.exception.department;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException() {
        super("Department not found");
    }
}
