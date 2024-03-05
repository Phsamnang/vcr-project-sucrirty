package com.kosign.vcrprojectsecurity.payload.category;

import com.kosign.vcrprojectsecurity.payload.product.ProductResponse;
import lombok.Builder;

import java.util.List;
@Builder
public record MainCategoryResponse(Long categoryId, String categoryName, List<ProductResponse> products) {
}
