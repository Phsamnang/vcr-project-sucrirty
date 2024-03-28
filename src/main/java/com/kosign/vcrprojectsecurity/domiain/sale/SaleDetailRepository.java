package com.kosign.vcrprojectsecurity.domiain.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail,Long> {
    @Query(value = "select sum(sd.saleAmount) from SaleDetail sd where sd.sale=?1")
    BigDecimal getTotalAmount(Sale sale);

}
