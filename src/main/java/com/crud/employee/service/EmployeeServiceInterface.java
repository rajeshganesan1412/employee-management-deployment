package com.crud.employee.service;

import com.crud.employee.model.Employee;

import java.util.List;

public interface EmployeeServiceInterface {

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    Employee addEmployeeDetails(Employee employee);

    Employee editEmployeeDetails(Employee employee, Long id);

    Employee deleteEmployeeById(Long id);
}
