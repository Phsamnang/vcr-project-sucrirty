package com.kosign.vcrprojectsecurity.service.table;


import com.kosign.vcrprojectsecurity.domiain.table.TableSale;
import com.kosign.vcrprojectsecurity.domiain.table.TableSaleRepository;
import com.kosign.vcrprojectsecurity.payload.table.TableRequest;
import com.kosign.vcrprojectsecurity.payload.table.TableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    @Override
    public List<TableResponse> getAllTable() {
        Sort sort = Sort.by("name").ascending();
        var t=tableSaleRepository.findAll(sort);
        List<TableResponse>responses=t.stream().map(table->{
            return TableResponse.builder().status(table.getStatus())
                    .name(table.getName()).id(table.getId()).build();
        }).collect(Collectors.toList());
        return responses;
    }
}
