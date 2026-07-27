package com.SaralSewa.SaralSewa.shared.dto.request.action.customer;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoftDeleteProfileRequest extends ModelBase {

    @NotNull(message = "Profile ID is required")
    private Integer profileId;
}
