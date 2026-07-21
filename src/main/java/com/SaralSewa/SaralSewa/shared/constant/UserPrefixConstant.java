package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPrefixConstant {
    USER("USR"),
    CUSTOMER("CUS"),
    PROVIDER("PRV"),
    ADMIN("ADM"),
    BOOKING("BOK");

    private final String prefix;
}