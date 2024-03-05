package com.kosign.vcrprojectsecurity.payload.category;

import lombok.Builder;

@Builder
public record CategoryResponse(Long categoryId,String categoryName ) {
}
