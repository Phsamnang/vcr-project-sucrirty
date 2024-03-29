package com.kosign.vcrprojectsecurity.service.menu;


import com.kosign.vcrprojectsecurity.payload.menu.MenuDetailRequest;
import com.kosign.vcrprojectsecurity.payload.menu.MenuRequest;
import com.kosign.vcrprojectsecurity.payload.menu.MenuResponse;

import java.util.List;

public interface IMenuService {
    void createMenu(MenuRequest payload);
    void addMenuDetail(MenuDetailRequest payload);

    List<MenuResponse> getMenu(Long id);

    List<MenuResponse> getMenuSale(Long id);
}
