package com.kosign.vcrprojectsecurity.enums;

public enum Currency {
    RIEL("riel"),
    USD("usd");

    String value;

    Currency(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
