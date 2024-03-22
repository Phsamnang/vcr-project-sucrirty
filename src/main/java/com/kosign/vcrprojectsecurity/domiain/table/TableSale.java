package com.kosign.vcrprojectsecurity.domiain.table;

import com.kosign.vcrprojectsecurity.domiain.sale.Sale;
import com.kosign.vcrprojectsecurity.enums.TableStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_table_sale")
@Getter
@Setter
@NoArgsConstructor
public class TableSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String status = TableStatus.AVAILABLE.toString();
    @OneToMany(mappedBy = "tableSale")
    private List<Sale> sales = new ArrayList<>();

    @Builder
    public TableSale(String name) {
        this.name = name;
    }
}
