package com.kosign.vcrprojectsecurity.service.category;

import com.kosign.vcrprojectsecurity.payload.category.CategoryRequest;
import com.kosign.vcrprojectsecurity.payload.category.CategoryResponse;
import com.kosign.vcrprojectsecurity.payload.category.MainCategoryResponse;

import java.util.List;

public interface ICategoryService {
    void addNewCategory(CategoryRequest request);
    List<CategoryResponse>getAllCategories();
    MainCategoryResponse getCategoryById(Long id);
}
