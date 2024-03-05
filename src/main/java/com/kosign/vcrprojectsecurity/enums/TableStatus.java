package com.kosign.vcrprojectsecurity.enums;

public enum TableStatus {
    AVAILABLE("available"),
    UNAVAILABLE("unavailable"),
    PENDING("pending");

    private final String value;
    TableStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
