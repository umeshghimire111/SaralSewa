package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApprovalStatusConstant {
    PENDING("PENDING"),
    VERIFIED("VERIFIED"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    IS_ACTIVE("isActive"),
    IS_DELETED("isDeleted");

    private final String name;
}