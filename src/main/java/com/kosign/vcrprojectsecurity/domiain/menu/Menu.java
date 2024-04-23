package com.kosign.vcrprojectsecurity.domiain.menu;

import com.kosign.vcrprojectsecurity.domiain.category.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_menu")
@NoArgsConstructor
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    private String name;
    private Boolean isCooking=false;
    private BigDecimal price;
    private BigDecimal usd;
    private String image;
    @OneToMany(mappedBy = "menu")
    private List<MenuDetail>menuDetails=new ArrayList<>();

    @Builder
    public Menu(Category category, String name, BigDecimal price, BigDecimal usd, String image) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.usd = usd;
        this.image = image;
    }
}
