package com.kosign.vcrprojectsecurity.payload.product;

import com.kosign.vcrprojectsecurity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceRequest {
    private Long productId;
  private Currency currency;
  private BigDecimal amount;
}
