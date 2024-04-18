package com.kosign.vcrprojectsecurity.payload.sale;

public record SaleDetailRequest(Long tableId,Long saleId,Long menuId,Integer qty) {
}
