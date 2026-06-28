package com.SaralSewa.SaralSewa.shared.core.config.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleConstant {

    ADMIN("ADMIN", "System Administrator"),
    CUSTOMER("CUSTOMER", "Service Seeker"),
    PROVIDER("PROVIDER", "Service Provider");

    private final  String name;
    private final String description;

}
