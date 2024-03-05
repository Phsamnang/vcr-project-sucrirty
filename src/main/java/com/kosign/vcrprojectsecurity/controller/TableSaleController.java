package com.kosign.vcrprojectsecurity.controller;

import com.kosign.vcrprojectsecurity.payload.table.TableRequest;
import com.kosign.vcrprojectsecurity.service.table.ITableSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
