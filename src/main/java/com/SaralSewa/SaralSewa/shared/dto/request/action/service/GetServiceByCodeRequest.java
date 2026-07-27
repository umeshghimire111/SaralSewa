package com.SaralSewa.SaralSewa.shared.dto.request.action.service;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetServiceByCodeRequest extends ModelBase {

    @NotBlank(message = "Service code is required")
    @Size(min = 2, max = 50, message = "Service code must be between 2 and 50 characters")
    private String code;
}
