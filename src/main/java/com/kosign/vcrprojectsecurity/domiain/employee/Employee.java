package com.kosign.vcrprojectsecurity.domiain.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal salary;
    private String image;

    @Builder

    public Employee(String name, BigDecimal salary, String image) {
        this.name = name;
        this.salary = salary;
        this.image = image;
    }
}
