package com.kosign.vcrprojectsecurity.payload.sale;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Builder
public record SaleResponse(Long saleId, LocalDateTime saleDate, String tableName, BigDecimal totalAmount,
                           List<SaleDetailResponse> orders, String cashier,
                           String status) {
}
