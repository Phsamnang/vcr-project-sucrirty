package com.kosign.vcrprojectsecurity.domiain.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableSaleRepository extends JpaRepository<TableSale,Long> {
    TableSale findByName(String name);
    Optional<TableSale> findById(Long id);
}
