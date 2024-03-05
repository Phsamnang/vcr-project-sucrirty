package com.kosign.vcrprojectsecurity.domiain.Import;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ImportProductRepository extends JpaRepository<ImportProduct, Long> {
   ImportProduct findByImportDate(LocalDate date);
}
