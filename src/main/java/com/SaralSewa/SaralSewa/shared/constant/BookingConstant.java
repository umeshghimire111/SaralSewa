package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookingConstant {
    DEFAULT_STATUS("PENDING"),
    IS_ACTIVE("isActive"),
    IS_DELETED("isDeleted");

    private final String name;
}