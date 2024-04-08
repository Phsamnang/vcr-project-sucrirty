package com.kosign.vcrprojectsecurity.payload.sale;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
@Builder
public record SaleResponse(Long saleId,String tableName, BigDecimal totalAmount, List<SaleDetailResponse> orders) {
}
