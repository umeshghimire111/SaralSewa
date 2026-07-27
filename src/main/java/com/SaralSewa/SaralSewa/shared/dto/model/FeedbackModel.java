package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackModel extends ModelBase {
    private Integer userId;
    private String subject;
    private String message;
    private String feedbackType;
    private Boolean isActive;
    private Boolean isDeleted;
}
