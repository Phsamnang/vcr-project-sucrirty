package com.kosign.vcrprojectsecurity.service.menu;

import com.kosign.vcrprojectsecurity.common.api.StatusCode;
import com.kosign.vcrprojectsecurity.domiain.category.Category;
import com.kosign.vcrprojectsecurity.domiain.category.CategoryRepository;
import com.kosign.vcrprojectsecurity.domiain.menu.Menu;
import com.kosign.vcrprojectsecurity.domiain.menu.MenuDetail;
import com.kosign.vcrprojectsecurity.domiain.menu.MenuDetailRepository;
import com.kosign.vcrprojectsecurity.domiain.menu.MenuRepository;
import com.kosign.vcrprojectsecurity.domiain.product.Product;
import com.kosign.vcrprojectsecurity.domiain.product.ProductRepository;
import com.kosign.vcrprojectsecurity.domiain.stock.StockRepository;
import com.kosign.vcrprojectsecurity.exception.BusinessException;
import com.kosign.vcrprojectsecurity.exception.EntityNotFoundException;
import com.kosign.vcrprojectsecurity.payload.menu.MenuDetailRequest;
import com.kosign.vcrprojectsecurity.payload.menu.MenuRequest;
import com.kosign.vcrprojectsecurity.payload.menu.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService implements IMenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final MenuDetailRepository menuDetailRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    @Override
    public void createMenu(MenuRequest payload) {
        var category = categoryRepository.findById(payload.categoryId()).orElseThrow(() -> new EntityNotFoundException(Category.class, "Category not found"));
        menuRepository.save(Menu.builder().name(payload.name())
                .price(payload.price())
                .image(payload.image())
                .usd(payload.price().divide(BigDecimal.valueOf(4000)))
                .category(category).build());
    }

    @Override
    public void addMenuDetail(MenuDetailRequest payload) {
        var menu = menuRepository.findById(payload.menuId()).orElseThrow(() -> new EntityNotFoundException(Menu.class, "Menu not found"));
        var product = productRepository.findById(payload.productId()).orElseThrow(() -> new EntityNotFoundException(Product.class, "Product not found"));
        menuDetailRepository.save(
                MenuDetail.builder()
                        .menu(menu)
                        .product(product)
                        .numberMenu(payload.numberMenu())
                        .useProduct(payload.useProduct())
                        .build()
        );
    }

    @Override
    public List<MenuResponse> getMenu(Long id) {

        var menus = menuRepository.findAll();
        List<MenuResponse> responses = menus.stream().filter(m -> m.getCategory().getId() == id)
                .map(m -> {
                    return MenuResponse.builder()
                            .id(m.getId())
                            .category(m.getCategory().getName())
                            .name(m.getName())
                            .rielPrice(m.getPrice())
                            .usdPrice(m.getUsd())
                            .image(m.getImage())
                            .isCooking(m.getIsCooking())
                            .build();
                }).collect(Collectors.toList());
        return responses;
    }

    @Override
    public List<MenuResponse> getMenuSale(Long id) {
        var menus=menuRepository.getMenuSaleByCategory(id);
        List<MenuResponse>menuResponses=menus.stream().map((m)->{
            return MenuResponse.builder()
                    .image(m.getImage())
                    .id(m.getId())
                    .name(m.getName())

                    .rielPrice(m.getPrice())
                    .usdPrice(m.getUsd()).build();
        }).collect(Collectors.toList());
        return menuResponses;
    }

    @Override
    public void isCooking(Long id) {
        var menu=menuRepository.findById(id).orElseThrow(()->new BusinessException(StatusCode.NOT_FOUND));
        menu.setIsCooking(!menu.getIsCooking());
        menuRepository.save(menu);
    }

}
