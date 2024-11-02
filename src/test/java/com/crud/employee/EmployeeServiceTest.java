package com.crud.employee;

import com.crud.employee.enumuration.Department;
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
}
