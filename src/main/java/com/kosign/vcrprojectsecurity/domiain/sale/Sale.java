package com.kosign.vcrprojectsecurity.domiain.sale;
import com.kosign.vcrprojectsecurity.domiain.table.TableSale;
import com.kosign.vcrprojectsecurity.domiain.user.User;
import com.kosign.vcrprojectsecurity.enums.SaleStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "tbSale")
@Getter
@Setter
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime saleDate;
    private BigDecimal saleTotal= BigDecimal.valueOf(0);
    private BigDecimal receiveMoney=BigDecimal.valueOf(0);
    private String status= SaleStatus.UNPAID.toString();
    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private TableSale tableSale;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER)
    private List<SaleDetail> saleDetails = new ArrayList<>();

    @Builder
    public Sale(TableSale tableSale) {
        this.tableSale = tableSale;
    }
}
