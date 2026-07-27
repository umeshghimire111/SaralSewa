package com.SaralSewa.SaralSewa.shared.dto.request.delete;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookingRequest extends ModelBase {

    @NotNull(message = "Booking ID is required")
    private Integer bookingId;
}
