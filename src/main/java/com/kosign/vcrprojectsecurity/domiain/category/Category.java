package com.kosign.vcrprojectsecurity.domiain.category;

import com.kosign.vcrprojectsecurity.domiain.product.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_name",nullable = false,unique = true)
    private String name;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products=new ArrayList<>();
    @Builder
    public Category(String name) {
        this.name = name;
    }
}
