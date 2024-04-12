package com.kosign.vcrprojectsecurity.service.category;

import com.kosign.vcrprojectsecurity.domiain.category.Category;
import com.kosign.vcrprojectsecurity.domiain.category.CategoryRepository;
import com.kosign.vcrprojectsecurity.exception.EntityNotFoundException;
import com.kosign.vcrprojectsecurity.payload.category.CategoryRequest;
import com.kosign.vcrprojectsecurity.payload.category.CategoryResponse;
import com.kosign.vcrprojectsecurity.payload.category.MainCategoryResponse;
import com.kosign.vcrprojectsecurity.payload.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository repository;

    @Override
    public void addNewCategory(CategoryRequest request) {
        var c = repository.findByName(request.getName());
        if (c.isPresent()) {
            throw new IllegalArgumentException(request.getName() + " is already exist!");
        }
        repository.save(Category.builder().name(request.getName()).build());
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<CategoryResponse> categoryResponses = repository.findAll().stream()
                .map(category -> CategoryResponse.builder()
                        .categoryId(category.getId())
                        .categoryName(category.getName())
                        .build()).collect(Collectors.toList());
        return categoryResponses;
    }

    @Override
    public MainCategoryResponse getCategoryById(Long id) {
        var category = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Category.class, "id", id.toString()));
        List<ProductResponse> productResponses = category.getProducts().stream()
                .map(product -> ProductResponse.builder().productName(product.getName())
                        .productId(product.getId())
                        .categoryName(product.getCategory().getName()).build()).collect(Collectors.toList());
        return MainCategoryResponse
                .builder()
                .categoryId(category.getId())
                .categoryName(category.getName())
                .products(productResponses).build();
    }


}
