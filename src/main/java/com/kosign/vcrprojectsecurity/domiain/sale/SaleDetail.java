package com.kosign.vcrprojectsecurity.domiain.sale;

import com.kosign.vcrprojectsecurity.domiain.menu.Menu;
import com.kosign.vcrprojectsecurity.enums.SaleDetailStatus;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "sale_detail")
@NoArgsConstructor
@Setter
@Getter
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private Sale sale;
    @Column(name = "sale_qty")
    private Integer saleQty;
    @Column(name = "sale_price")
    private BigDecimal salePrice;
    @Column(name = "sale_amount")
    private BigDecimal saleAmount;
    private String status= SaleDetailStatus.WAIT.toString();
    @Builder
    public SaleDetail(Menu menu, Sale sale, Integer saleQty, BigDecimal salePrice, BigDecimal saleAmount) {
        this.menu = menu;
        this.sale = sale;
        this.saleQty = saleQty;
        this.salePrice = salePrice;
        this.saleAmount = saleAmount;
    }
}
