package com.kosign.vcrprojectsecurity.service.table;


import com.kosign.vcrprojectsecurity.payload.table.TableRequest;

public interface ITableSaleService {
    void createTable(TableRequest request) throws Throwable;
}
