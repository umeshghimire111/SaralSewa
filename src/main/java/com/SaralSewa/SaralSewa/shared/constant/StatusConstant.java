package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusConstant {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    PENDING("PENDING"),
    SUSPENDED("SUSPENDED"),
    IS_ACTIVE("isActive"),
    IS_DELETED("isDeleted");

    private final String name;
}