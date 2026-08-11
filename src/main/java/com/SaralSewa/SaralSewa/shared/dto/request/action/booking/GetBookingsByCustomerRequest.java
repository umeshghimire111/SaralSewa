package com.SaralSewa.SaralSewa.shared.dto.request.action.booking;

import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBookingsByCustomerRequest extends ModelBase {

    @NotNull(message = "Customer ID is required")
    private Integer customerId;

    private PageRequest pageRequest;
}
