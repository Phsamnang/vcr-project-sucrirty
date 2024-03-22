package com.kosign.vcrprojectsecurity.controller;

import com.kosign.vcrprojectsecurity.payload.table.TableRequest;
import com.kosign.vcrprojectsecurity.service.table.ITableSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TableSaleController extends VCRRestController{
    private final ITableSaleService service;
    @PostMapping("/table")
    public ResponseEntity<?>createTable(@RequestBody TableRequest request) throws Throwable {
        service.createTable(request);
        return ok();
    }
    @GetMapping("/table")
    public ResponseEntity<?>createTable() throws Throwable {

        return ok(service.getAllTable());
    }
}
