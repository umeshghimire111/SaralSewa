package com.SaralSewa.SaralSewa.shared.dto.request.action.provider;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveProviderRequest extends ModelBase {

    @NotNull(message = "Provider ID is required")
    private Integer providerId;
}
