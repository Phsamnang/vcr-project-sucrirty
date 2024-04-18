package com.kosign.vcrprojectsecurity.service.table;


import com.kosign.vcrprojectsecurity.payload.table.TableRequest;
import com.kosign.vcrprojectsecurity.payload.table.TableResponse;

import java.util.List;

public interface ITableSaleService {
    void createTable(TableRequest request) throws Throwable;

    List<TableResponse> getAllTable();

    TableResponse getTableById(Long id);
}
