package com.kosign.vcrprojectsecurity.payload.sale;

import lombok.Builder;

@Builder
public record ChefFoodResponse(String name, Integer qty, String status, String table) {
}
