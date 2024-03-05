package com.kosign.vcrprojectsecurity.domiain.Import;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbImport")
@Getter
@Setter
@NoArgsConstructor
public class ImportProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "impDate")
    @CreatedDate
    private LocalDate importDate=LocalDate.now();
    @Column(name = "impTotal")
    private BigDecimal importTotal=BigDecimal.valueOf(0);
    @Column(name = "impUsdTotal")
    private BigDecimal importUsdTotal=BigDecimal.valueOf(0);
    @OneToMany(mappedBy = "importProduct",fetch = FetchType.LAZY)
    private List<ImportDetail> importDetails=new ArrayList<>();
}
