package com.kosign.vcrprojectsecurity.service.employee;

import com.kosign.vcrprojectsecurity.domiain.employee.Employee;
import com.kosign.vcrprojectsecurity.payload.employee.EmployeeRequest;

import java.util.List;

public interface IEmployee {
    void addEmployee(EmployeeRequest request);
    List<Employee> getAllEmployees();
}
