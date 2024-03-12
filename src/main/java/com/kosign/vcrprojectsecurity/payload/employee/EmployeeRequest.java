package com.kosign.vcrprojectsecurity.payload.employee;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record EmployeeRequest(String name, BigDecimal salary) {
}
