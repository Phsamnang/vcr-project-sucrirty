package com.kosign.vcrprojectsecurity.service.menu;


import com.kosign.vcrprojectsecurity.payload.menu.MenuDetailRequest;
import com.kosign.vcrprojectsecurity.payload.menu.MenuRequest;

public interface IMenuService {
    void createMenu(MenuRequest payload);
    void addMenuDetail(MenuDetailRequest payload);
}
