package com.kosign.vcrprojectsecurity.domiain.menu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDetailRepository extends JpaRepository<MenuDetail,Long> {
    MenuDetail findById_MenuId(Long menuId);
}
