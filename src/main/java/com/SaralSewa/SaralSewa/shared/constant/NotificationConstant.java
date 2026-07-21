package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationConstant {
    BOOKING("BOOKING"),
    PAYMENT("PAYMENT"),
    SYSTEM("SYSTEM"),
    PROMOTIONAL("PROMOTIONAL"),
    IS_ACTIVE("isActive"),
    IS_DELETED("isDeleted");

    private final String name;
}