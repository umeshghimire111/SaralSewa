package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleConstant {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER"),
    PROVIDER("PROVIDER"),
    IS_ACTIVE("isActive"),
    IS_DELETED("isDeleted");

    private final String name;
}