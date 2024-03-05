package com.kosign.vcrprojectsecurity.domiain.stock;

import com.kosign.vcrprojectsecurity.domiain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_stock")
@Getter
@Setter
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
    double number;

    public Stock(Product product, double number) {
        this.product = product;
        this.number = number;
    }
}
