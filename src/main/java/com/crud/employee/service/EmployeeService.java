package com.crud.employee.service;

import com.crud.employee.exception.EmployeeNotFoundException;
import com.crud.employee.model.Employee;
import com.crud.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService implements EmployeeServiceInterface {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(Long id) {
        log.info("Entering Employee service to get employee details for {}", id);
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee details not found in DB", HttpStatus.NOT_FOUND);
        }
        return employee.get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.info("Entering Employee service to get all employee details");
        List<Employee> employeeList = employeeRepository.findAll();
         if(employeeList.isEmpty()) {
             throw new EmployeeNotFoundException("Employee details not found in DB", HttpStatus.NOT_FOUND);
         }
         return employeeList;
    }

    @Override
    public Employee addEmployeeDetails(Employee employee) {
        log.info("Entering Employee service to add employee details and the request is: {}", employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee editEmployeeDetails(Employee employee, Long id) {
        log.info("Entering Employee service to edit/update employee details and the request is: {}", employee);
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee details not found in DB", HttpStatus.NOT_FOUND);
        }
        existingEmployee = Optional.of(Employee.builder().id(id)
                .name(employee.getName())
                .age(employee.getAge())
                .department(employee.getDepartment())
                .salary(employee.getSalary()).build());
        return employeeRepository.save(existingEmployee.get());
    }

    @Override
    public Employee deleteEmployeeById(Long id) {
        log.info("Entering Employee service to delete employee details for {}", id);
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee details not found in DB", HttpStatus.NOT_FOUND);
        }
        employeeRepository.deleteById(id);
        return employee.get();
    }
}
