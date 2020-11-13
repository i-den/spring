package org.example.classes.mapping.enums;

public enum PaymentType {
    CASH("CASH"),
    CC("CC");

    private String type;

    PaymentType(String type) {
        this.type = type;
    }
}
