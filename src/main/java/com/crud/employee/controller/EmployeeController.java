package com.crud.employee.controller;

import com.crud.employee.model.Employee;
import com.crud.employee.service.EmployeeServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeServiceInterface employeeServiceInterface;

    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
        log.info("Entering controller to get all employee details");
        return employeeServiceInterface.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        log.info("Entering controller to get employee details for {}", id);
        return employeeServiceInterface.getEmployeeById(id);
    }

    @PostMapping("/employee")
    public Employee addEmployeeDetails(@Valid @RequestBody Employee employee) {
        log.info("Entering controller to add employee details and the request is: {}", employee);
        return employeeServiceInterface.addEmployeeDetails(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployeeDetails(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        log.info("Entering controller to update employee details and the request is: {}", employee);
        return employeeServiceInterface.editEmployeeDetails(employee, id);
    }

    @DeleteMapping("/employee/{id}")
    public Employee deleteByEmployeeId(@PathVariable Long id) {
        log.info("Entering controller to delete employee details for {}", id);
        return employeeServiceInterface.deleteEmployeeById(id);
    }
}
