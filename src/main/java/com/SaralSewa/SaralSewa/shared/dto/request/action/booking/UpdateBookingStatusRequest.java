package com.SaralSewa.SaralSewa.shared.dto.request.action.booking;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookingStatusRequest extends ModelBase {

    @NotNull(message = "Booking ID is required")
    private Integer bookingId;

    @NotBlank(message = "Status code is required")
    private String statusCode;
}
