package com.kosign.vcrprojectsecurity.payload.importDetial;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
@Builder
public record ImportDetailMainResponse(Long importId, BigDecimal importTotal,BigDecimal usdTotal, List<ImportDetailResponse> importDetails) {
}
