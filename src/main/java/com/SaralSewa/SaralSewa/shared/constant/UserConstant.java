package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserConstant {
    DEFAULT_ROLE_CODE("CUSTOMER"),
    DEFAULT_STATUS_CODE("ACTIVE"),
    IS_ACTIVE("isActive"),
    IS_DELETED("isDeleted");

    private final String name;
}