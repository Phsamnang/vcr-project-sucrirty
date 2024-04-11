package com.kosign.vcrprojectsecurity.controller;

import com.kosign.vcrprojectsecurity.payload.sale.SaleDetailRequest;
import com.kosign.vcrprojectsecurity.payload.sale.SaleRequest;
import com.kosign.vcrprojectsecurity.service.sale.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class SaleController extends VCRRestController {
    private final ISaleService service;

    @PostMapping("/sale")
    public ResponseEntity<?> createSale(@RequestBody SaleRequest request) {
        service.createSale(request);
        return ok();
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody SaleDetailRequest request) {
        service.createOrder(request);
        return ok();
    }

    @PatchMapping("/table/{tableName}")
    public ResponseEntity<?> finishOrder(@PathVariable("tableName") String name) {
        service.finishOrder(name);
        return ok();
    }

    @PutMapping("/sale/{id}")
    public ResponseEntity<?> salePayment(@PathVariable("id") Long id, @RequestParam("money") BigDecimal money) {
        service.salePayment(id, money);
        return ok();
    }
    @GetMapping("/sale/table/{id}")
    public ResponseEntity<?> getSaleBYTable(@PathVariable("id") Long tableId) {
        return ok(service.getSaleByTable(tableId));
    }

    @DeleteMapping("/sale/table/item/{id}")
    public ResponseEntity<?> removedItem(@PathVariable("id") Long id) {
        service.removedOrder(id);
        return ok();
    }
}
