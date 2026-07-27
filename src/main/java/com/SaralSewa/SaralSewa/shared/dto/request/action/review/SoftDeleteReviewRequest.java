package com.SaralSewa.SaralSewa.shared.dto.request.action.review;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoftDeleteReviewRequest extends ModelBase {

    @NotNull(message = "Review ID is required")
    private Integer reviewId;
}
