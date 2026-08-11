package com.SaralSewa.SaralSewa.shared.dto.request.action.booking;

import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBookingsByProviderRequest extends ModelBase {

    @NotNull(message = "Provider ID is required")
    private Integer providerId;

    private PageRequest pageRequest;
}
