package com.kosign.vcrprojectsecurity.service.table;

import com.vcr.vcr_project.model.table.TableSale;
import com.vcr.vcr_project.model.table.TableSaleRepository;
import com.vcr.vcr_project.payload.table.TableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TableSaleService implements ITableSaleService{
    private final TableSaleRepository tableSaleRepository;

    @Override
    public void createTable(TableRequest request)throws Throwable {
        if(tableSaleRepository.findByName(request.name())!=null){
            throw new IllegalArgumentException("This table name already exist");
        }
        tableSaleRepository.save(TableSale.builder().name(request.name()).build());
    }
}
