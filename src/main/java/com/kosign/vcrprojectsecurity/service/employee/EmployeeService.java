package com.kosign.vcrprojectsecurity.service.employee;

import com.kosign.vcrprojectsecurity.domiain.employee.Employee;
import com.kosign.vcrprojectsecurity.domiain.employee.EmployeeRepository;
import com.kosign.vcrprojectsecurity.payload.employee.EmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployee{
    private final EmployeeRepository employeeRepository;
    @Override
    public void addEmployee(EmployeeRequest request) {
       employeeRepository.save(
               Employee.builder().name(request.name()).salary(request.salary()).build()
       );
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
