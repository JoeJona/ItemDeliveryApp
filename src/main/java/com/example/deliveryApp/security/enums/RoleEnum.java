package com.example.deliveryApp.security.enums;

public enum RoleEnum {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER"),
    DELIVERY_AGENT("DELIVERY_AGENT"),
    ITEM_SELLER("ITEM_SELLER");

    private final String roleName;

    private RoleEnum(String roleName) {
        this.roleName=roleName;
    }


    public String getRoleName() {
        return roleName;
    }
}
