package com.kosign.vcrprojectsecurity.domiain.stock;

import com.kosign.vcrprojectsecurity.domiain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Stock findByProduct(Product product);
}
