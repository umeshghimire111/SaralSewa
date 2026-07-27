package com.SaralSewa.SaralSewa.shared.dto.request.action.feedback;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoftDeleteFeedbackRequest extends ModelBase {

    @NotNull(message = "Feedback ID is required")
    private Integer feedbackId;
}
