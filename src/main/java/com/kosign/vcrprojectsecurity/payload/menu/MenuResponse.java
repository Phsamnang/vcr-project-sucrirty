package com.kosign.vcrprojectsecurity.payload.menu;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record MenuResponse(Long id,String name, String category,BigDecimal rielPrice,BigDecimal usdPrice,String image) {
}
