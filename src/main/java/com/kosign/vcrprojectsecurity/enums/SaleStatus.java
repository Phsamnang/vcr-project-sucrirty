package com.kosign.vcrprojectsecurity.enums;

public enum SaleStatus {
    PAID("paid"),
    UNPAID("unpaid");

    String value;

    SaleStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
