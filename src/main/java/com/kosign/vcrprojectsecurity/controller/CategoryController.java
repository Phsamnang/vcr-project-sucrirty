package com.kosign.vcrprojectsecurity.controller;

import com.kosign.vcrprojectsecurity.payload.category.CategoryRequest;
import com.kosign.vcrprojectsecurity.service.category.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class CategoryController extends VCRRestController {
    private final ICategoryService service;

    public CategoryController(ICategoryService service) {
        this.service = service;
    }
    @PostMapping("/category")
    public ResponseEntity<?> addNewCategory(@RequestBody CategoryRequest request) {
       service.addNewCategory(request);
        return ok();
    }
    @GetMapping("/categories")
    public ResponseEntity<?> addNewCategory() {

        return ok(service.getAllCategories()) ;
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        return ok(service.getCategoryById(id));
    }
}
