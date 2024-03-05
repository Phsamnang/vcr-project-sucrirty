package com.kosign.vcrprojectsecurity.payload.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Product can not null")
    private String name;
    private BigDecimal price;
    @NotNull(message = "Category ID is required!")
    private Long categoryId;
    private String image;
    private Boolean isAvailable;
}
