package com.kosign.vcrprojectsecurity.domiain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.category.id = ?1")
    Product getProductByCategoryId(Long id);
}
