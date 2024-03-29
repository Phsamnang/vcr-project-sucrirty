package com.kosign.vcrprojectsecurity.domiain.menu;

import com.kosign.vcrprojectsecurity.payload.menu.MenusSaleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    @Query("select m.id as id ,m.name as name, m.image as image, m.price as price, m.usd as usd \n" +
            "from Menu m\n" +
            "         left join MenuDetail md on m.id = md.menu.id\n" +
            "         left join Stock s on md.product.id=s.product.id where s.number>0 and m.category.id=?1")
    List<MenusSaleResponse> getMenuSaleByCategory(Long id);
}
