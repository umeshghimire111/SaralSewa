package com.SaralSewa.SaralSewa.shared.dto.request.action.status;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeactivateStatusRequest extends ModelBase {

    @NotNull(message = "Status ID is required")
    private Integer statusId;
}
