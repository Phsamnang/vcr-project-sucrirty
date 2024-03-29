package com.kosign.vcrprojectsecurity.payload.menu;

import java.math.BigDecimal;

public interface MenusSaleResponse {
    Long getId();
    String getName();
    String getImage();
    BigDecimal getPrice();
    BigDecimal getUsd();
}
