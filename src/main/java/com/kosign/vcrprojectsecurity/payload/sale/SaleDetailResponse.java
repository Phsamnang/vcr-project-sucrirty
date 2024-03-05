package com.kosign.vcrprojectsecurity.payload.sale;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record SaleDetailResponse(String item, Integer QTY, BigDecimal price,BigDecimal amount,String status) {
}
