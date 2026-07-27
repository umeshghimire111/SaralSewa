package com.SaralSewa.SaralSewa.shared.dto.request.update;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFeedbackRequest extends ModelBase {

    @Size(min = 2, max = 150, message = "Subject must be between 2 and 150 characters")
    private String subject;

    @Size(min = 2, max = 500, message = "Message must be between 2 and 500 characters")
    private String message;

    @Size(max = 50, message = "Feedback type must be less than 50 characters")
    private String feedbackType;
}
