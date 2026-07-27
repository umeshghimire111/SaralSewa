package com.SaralSewa.SaralSewa.shared.dto.request.action.approval;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoftDeleteApprovalStatusRequest extends ModelBase {

    @NotNull(message = "Approval Status ID is required")
    private Integer approvalStatusId;
}
