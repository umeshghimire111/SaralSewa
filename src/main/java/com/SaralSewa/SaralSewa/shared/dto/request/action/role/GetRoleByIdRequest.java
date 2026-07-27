package com.SaralSewa.SaralSewa.shared.dto.request.action.role;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRoleByIdRequest extends ModelBase {

    @NotNull(message = "Role ID is required")
    private Integer roleId;
}
