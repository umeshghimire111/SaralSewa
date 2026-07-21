package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceConstant {
    DEFAULT_STATUS("ACTIVE"),
    IS_ACTIVE("isActive"),
    IS_DELETED("isDeleted");

    private final String name;
}