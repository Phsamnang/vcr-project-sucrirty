package com.kosign.vcrprojectsecurity.service.sale;


import com.kosign.vcrprojectsecurity.payload.sale.SaleDetailRequest;
import com.kosign.vcrprojectsecurity.payload.sale.SaleRequest;
import com.kosign.vcrprojectsecurity.payload.sale.SaleResponse;

public interface ISaleService {
    void createSale(SaleRequest request);

    void createOrder(SaleDetailRequest request);
    SaleResponse getSaleByTable(Long tableId);
}
