package com.SaralSewa.SaralSewa.shared.dto.request.action.service;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeactivateServiceRequest extends ModelBase {

    @NotNull(message = "Service ID is required")
    private Integer serviceId;
}
