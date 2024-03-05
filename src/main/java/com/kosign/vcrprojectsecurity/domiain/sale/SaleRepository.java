package com.kosign.vcrprojectsecurity.domiain.sale;

import com.kosign.vcrprojectsecurity.domiain.table.TableSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    Sale findByTableSaleAndStatus(TableSale tableSale, String status);
}
