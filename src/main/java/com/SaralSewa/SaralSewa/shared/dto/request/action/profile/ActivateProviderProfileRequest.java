package com.SaralSewa.SaralSewa.shared.dto.request.action.profile;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivateProviderProfileRequest extends ModelBase {

    @NotNull(message = "Profile ID is required")
    private Integer profileId;
}
