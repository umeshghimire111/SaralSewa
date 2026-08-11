package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusConstant {

    ACTIVE("ACTIVE"),
    DELETED("DELETED"),
    PENDING("PENDING"),
    BLOCKED("BLOCKED"),
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED");


    private final String name;
}