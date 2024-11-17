package com.crud.employee;

import com.crud.employee.enumuration.Department;
import com.crud.employee.exception.EmployeeNotFoundException;
import com.crud.employee.model.Employee;
import com.crud.employee.repository.EmployeeRepository;
import com.crud.employee.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    @Test
    public void testGetEmployeeById() {
        Employee employee = Employee.builder()
                .id(Long.valueOf(123))
                .name("Rajesh")
                .age(28)
                .department(Department.IT)
                .salary(BigDecimal.valueOf(1400.00)).build();
        Mockito.when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        Employee employeeResult = employeeService.getEmployeeById(Long.valueOf(123));

        Assertions.assertEquals("Rajesh", employeeResult.getName());
    }

    @Test
    public void testGetEmployeeByIdNegative() {
        Mockito.when(employeeRepository.findById(any())).thenReturn(Optional.empty());
        EmployeeNotFoundException exception = Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(Long.valueOf(123)));

        Assertions.assertEquals("Employee details not found in DB", exception.getErrorMessage());
    }

    @Test
    public void testGetEmployees() {
        Employee employee1 = Employee.builder()
                .id(Long.valueOf(123))
                .name("Rajesh")
                .age(28)
                .department(Department.IT)
                .salary(BigDecimal.valueOf(1400.00)).build();
        Employee employee2 = Employee.builder()
                .id(Long.valueOf(134))
                .name("Blessy")
                .age(20)
                .department(Department.FINANCE)
                .salary(BigDecimal.valueOf(12000.00)).build();
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> employeeResult = employeeService.getAllEmployees();

        Assertions.assertEquals("Rajesh", employeeResult.get(0).getName());
    }

    @Test
    public void testGetAllEmployeesNegative() {
        Mockito.when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
        EmployeeNotFoundException exception = Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.getAllEmployees());

        Assertions.assertEquals("Employee details not found in DB", exception.getErrorMessage());
    }

    @Test
    public void testAddEmployeeDetails() {
        Employee employee = Employee.builder()
                .id(Long.valueOf(123))
                .name("Rajesh")
                .age(28)
                .department(Department.IT)
                .salary(BigDecimal.valueOf(1400.00)).build();
        Mockito.when(employeeRepository.save(any())).thenReturn(employee);
        Employee employees = employeeService.addEmployeeDetails(employee);

        Assertions.assertEquals("Rajesh", employees.getName());
    }

    @Test
    public void testEditEmployeeDetails() {
        Employee employee = Employee.builder()
                .id(Long.valueOf(123))
                .name("Rajesh")
                .age(28)
                .department(Department.IT)
                .salary(BigDecimal.valueOf(1400.00)).build();
        Mockito.when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepository.save(any())).thenReturn(employee);
        Employee employees = employeeService.editEmployeeDetails(employee, employee.getId());

        Assertions.assertEquals("Rajesh", employees.getName());
    }

    @Test
    public void testEditEmployeeDetailsNegative() {
        Employee employee = Employee.builder()
                .id(Long.valueOf(123))
                .name("Rajesh")
                .age(28)
                .department(Department.IT)
                .salary(BigDecimal.valueOf(1400.00)).build();
        Mockito.when(employeeRepository.findById(any())).thenReturn(Optional.empty());
        EmployeeNotFoundException exception = Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.editEmployeeDetails(employee, employee.getId()));

        Assertions.assertEquals("Employee details not found in DB", exception.getErrorMessage());
    }

    @Test
    public void testDeleteEmployeeById() {
        Employee employee = Employee.builder()
                .id(Long.valueOf(123))
                .name("Rajesh")
                .age(28)
                .department(Department.IT)
                .salary(BigDecimal.valueOf(1400.00)).build();
        Mockito.when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        employeeService.deleteEmployeeById(Long.valueOf(123));

        Assertions.assertEquals("Rajesh", employee.getName());
    }

    @Test
    public void testDeleteEmployeeByIdNegative() {
        Employee employee = Employee.builder()
                .id(Long.valueOf(123))
                .name("Rajesh")
                .age(28)
                .department(Department.IT)
                .salary(BigDecimal.valueOf(1400.00)).build();
        Mockito.when(employeeRepository.findById(any())).thenReturn(Optional.empty());
        EmployeeNotFoundException exception = Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployeeById(employee.getId()));

        Assertions.assertEquals("Employee details not found in DB", exception.getErrorMessage());
    }

}
