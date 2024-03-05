package com.kosign.vcrprojectsecurity.domiain.Import;
import com.kosign.vcrprojectsecurity.domiain.product.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "import_detail")
public class ImportDetail {
    @EmbeddedId
    private ImportDetailId id = new ImportDetailId();
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @MapsId("productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "import_id", referencedColumnName = "id")
    @MapsId("importId")
    private ImportProduct importProduct;
    @Column(name = "import_qty", nullable = false)
    private double importQty;
    @Column(name = "import_price", nullable = false)
    private BigDecimal importPrice;
    @Column(name = "amount")
    private BigDecimal amount;

    @Builder
    public ImportDetail(Product product, ImportProduct importProduct, double importQty, BigDecimal importPrice, BigDecimal amount) {
        this.product = product;
        this.importProduct = importProduct;
        this.importQty = importQty;
        this.importPrice = importPrice;
        this.amount = amount;
    }
}
