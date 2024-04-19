package com.kosign.vcrprojectsecurity.service.sale;


import com.kosign.vcrprojectsecurity.payload.sale.SaleDetailRequest;
import com.kosign.vcrprojectsecurity.payload.sale.SaleRequest;
import com.kosign.vcrprojectsecurity.payload.sale.SaleResponse;

import java.math.BigDecimal;

public interface ISaleService {
    void bookingTable(SaleRequest request);

    void createOrder(SaleDetailRequest request);
    SaleResponse getSaleByTable(Long tableId);
    void removedOrder(Long id);
void finishOrder(String tableName);
    void salePayment(Long id,BigDecimal money);
}
