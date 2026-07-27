package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewModel extends ModelBase {
    private Integer bookingId;
    private Integer customerId;
    private Integer providerId;
    private Integer rating;
    private String reviewTitle;
    private String reviewMessage;
    private Boolean isActive;
    private Boolean isDeleted;
}
