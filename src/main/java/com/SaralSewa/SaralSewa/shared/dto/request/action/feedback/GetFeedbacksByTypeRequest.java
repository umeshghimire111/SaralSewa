package com.SaralSewa.SaralSewa.shared.dto.request.action.feedback;

import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetFeedbacksByTypeRequest extends ModelBase {

    @NotBlank(message = "Feedback type is required")
    private String feedbackType;

    private PageRequest pageRequest;
}
