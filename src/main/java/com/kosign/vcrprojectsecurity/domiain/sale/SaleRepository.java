package com.kosign.vcrprojectsecurity.domiain.sale;

import com.kosign.vcrprojectsecurity.domiain.table.TableSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    Sale findByTableSaleAndStatus(TableSale tableSale, String status);

    @Query("select distinct s from Sale s where s.tableSale.id = ?1 and s.tableSale.status = ?2 order by s.id desc limit 1")
    Sale findByTableSale_IdAndTableSale_Status(Long id, String status);

    @Query("select s from Sale s where s.tableSale.id = ?1 order by s.id desc limit 1")
    Sale findByTableSale_IdAndStatus(Long id);
}
