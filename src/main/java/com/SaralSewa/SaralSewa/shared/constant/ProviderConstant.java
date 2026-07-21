package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderConstant {
    DEFAULT_STATUS("ACTIVE"),
    DEFAULT_APPROVAL("PENDING"),
    IS_ACTIVE("isActive"),
    IS_DELETED("isDeleted");

    private final String name;
}