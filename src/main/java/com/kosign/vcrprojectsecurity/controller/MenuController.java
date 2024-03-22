package com.kosign.vcrprojectsecurity.controller;

import com.kosign.vcrprojectsecurity.payload.menu.MenuDetailRequest;
import com.kosign.vcrprojectsecurity.payload.menu.MenuRequest;
import com.kosign.vcrprojectsecurity.service.menu.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class MenuController extends VCRRestController{
    private final IMenuService service;
    @PostMapping("/menu")
    public ResponseEntity<?>createMenu(@RequestBody MenuRequest payload){
        service.createMenu(payload);
        return ok();
    }
    @PostMapping("/menu-detail")
    public ResponseEntity<?>createMenuDetail(@RequestBody MenuDetailRequest payload){
        service.addMenuDetail(payload);
        return ok();
    }

    @GetMapping("/menu")
    public ResponseEntity<?> getMenu(@RequestParam(name = "cate_id", defaultValue = "1") Long id) {
        return ok(service.getMenu(id));
    }
}
