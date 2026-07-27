package com.SaralSewa.SaralSewa.shared.dto.request.delete;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCustomerProfileRequest extends ModelBase {

    @NotNull(message = "Customer Profile ID is required")
    private Integer customerProfileId;
}
