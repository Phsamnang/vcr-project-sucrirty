package com.kosign.vcrprojectsecurity.controller;

import com.kosign.vcrprojectsecurity.payload.employee.EmployeeRequest;
import com.kosign.vcrprojectsecurity.service.employee.IEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class EmployeeController extends VCRRestController {
    private final IEmployee service;
    @PostMapping("/employee")
    public ResponseEntity<?>addEmployee(@RequestBody EmployeeRequest request){
        System.err.println("Hksdmgdslkrgmfv"+request);
        service.addEmployee(request);
       return ok();
    }
    @GetMapping("/employee")
    public ResponseEntity<?>getAllEmployee(){
        return ok(service.getAllEmployees());
    }
}
