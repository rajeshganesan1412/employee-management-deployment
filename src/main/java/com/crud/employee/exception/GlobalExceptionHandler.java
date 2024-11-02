package com.crud.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Error> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBuilder(request.getDescription(false), ex.getHttpCode().value(), ex.getErrorMessage()));

    }

    private Error errorBuilder(String url, Integer httpCode, String errorMessage) {
        return Error.builder().httpStatusCode(httpCode).url(url).message(errorMessage).build();
    }
}
