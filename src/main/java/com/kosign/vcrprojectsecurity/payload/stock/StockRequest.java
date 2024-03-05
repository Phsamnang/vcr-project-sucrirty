package com.kosign.vcrprojectsecurity.payload.stock;

import lombok.Builder;

@Builder
public record StockRequest(Long productId,double qty) {
}
