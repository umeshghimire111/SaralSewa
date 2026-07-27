package com.SaralSewa.SaralSewa.shared.dto.request.action.feedback;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import com.SaralSewa.SaralSewa.shared.dto.common.PageRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetFeedbacksByUserRequest extends ModelBase {

    @NotNull(message = "User ID is required")
    private Integer userId;

    private PageRequest pageRequest;
}
