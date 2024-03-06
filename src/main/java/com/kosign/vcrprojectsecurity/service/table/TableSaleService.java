package com.kosign.vcrprojectsecurity.service.table;


import com.kosign.vcrprojectsecurity.domiain.table.TableSale;
import com.kosign.vcrprojectsecurity.domiain.table.TableSaleRepository;
import com.kosign.vcrprojectsecurity.payload.table.TableRequest;
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
