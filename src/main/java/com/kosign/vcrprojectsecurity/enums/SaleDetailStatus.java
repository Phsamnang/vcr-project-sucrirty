package com.kosign.vcrprojectsecurity.enums;

public enum SaleDetailStatus {
    WAIT("wait"),
    PROGRESS("progress"),
    DONE("done");
    String value;

    SaleDetailStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
