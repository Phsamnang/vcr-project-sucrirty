package com.kosign.vcrprojectsecurity.payload.importDetial;

import java.math.BigDecimal;

public record ImportDetailRequest(Long productId, Long importId, double importQty, BigDecimal importPrice) {
}
