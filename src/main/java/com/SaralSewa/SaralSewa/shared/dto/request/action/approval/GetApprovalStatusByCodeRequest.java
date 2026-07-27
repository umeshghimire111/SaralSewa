package com.SaralSewa.SaralSewa.shared.dto.request.action.approval;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetApprovalStatusByCodeRequest extends ModelBase {

    @NotBlank(message = "Approval status code is required")
    private String code;
}
