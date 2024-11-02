package com.crud.employee.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class EmployeeNotFoundException extends RuntimeException {

    private String errorMessage;
    private HttpStatus httpCode;

}
