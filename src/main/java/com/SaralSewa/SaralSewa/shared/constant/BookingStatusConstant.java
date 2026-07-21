package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookingStatusConstant {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED"),
    NO_SHOW("NO_SHOW"),
    IS_ACTIVE("isActive"),
    IS_DELETED("isDeleted");

    private final String name;
}