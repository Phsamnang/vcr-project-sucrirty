package com.kosign.vcrprojectsecurity.domiain.menu;

import com.kosign.vcrprojectsecurity.domiain.product.Product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menu_detail")
@Getter
@Setter
@NoArgsConstructor
public class MenuDetail {
    @EmbeddedId
    private MenuDetailId id = new MenuDetailId();
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @MapsId("productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    @MapsId("menuId")
    private Menu menu;
    private double numberMenu;
    private double useProduct;
    private double totalUse;

    @Builder
    public MenuDetail(Product product, Menu menu, double numberMenu, double useProduct) {
        this.product = product;
        this.menu = menu;
        this.numberMenu = numberMenu;
        this.useProduct = useProduct;
        this.totalUse=numberMenu*useProduct;
    }
}
