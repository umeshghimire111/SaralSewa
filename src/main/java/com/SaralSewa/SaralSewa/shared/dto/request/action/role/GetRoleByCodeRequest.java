package com.SaralSewa.SaralSewa.shared.dto.request.action.role;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRoleByCodeRequest extends ModelBase {

    @NotBlank(message = "Role code is required")
    private String code;
}
