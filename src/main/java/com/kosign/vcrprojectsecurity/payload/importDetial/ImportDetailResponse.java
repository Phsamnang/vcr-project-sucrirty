package com.kosign.vcrprojectsecurity.payload.importDetial;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ImportDetailResponse(String productName,String category, double QTY, BigDecimal price,BigDecimal amount) {
}
