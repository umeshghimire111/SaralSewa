package com.SaralSewa.SaralSewa.shared.core.config.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusConstant {
    ACTIVE("ACTIVE", "User account is active"),
    INACTIVE("INACTIVE", "User account is inactive"),
    BLOCKED("BLOCKED", "User account is blocked"),
    DELETED("DELETED", "User account is deleted"),
    PENDING("PENDING", "User account is pending verification"),
    SUSPENDED("SUSPENDED", "User account is suspended");

    private final String name;
    private final String description;
}