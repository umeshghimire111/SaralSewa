package com.SaralSewa.SaralSewa.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeedbackConstant {
    COMPLAINT("COMPLAINT"),
    SUGGESTION("SUGGESTION"),
    APPRECIATION("APPRECIATION"),
    INQUIRY("INQUIRY"),
    IS_ACTIVE("isActive"),
    IS_DELETED("isDeleted");

    private final String name;
}