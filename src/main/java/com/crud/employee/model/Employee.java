package com.crud.employee.model;

import com.crud.employee.enumuration.Department;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Name should be blank")
    private String name;

    @NotNull(message = "Age should not be blank")
    private Integer age;

    @NotNull(message = "Department should not be blank")
    private Department department;

    @NotNull(message = "Salary should not be blank")
    private BigDecimal salary;
}
