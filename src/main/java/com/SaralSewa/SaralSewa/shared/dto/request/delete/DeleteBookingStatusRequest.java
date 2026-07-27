package com.SaralSewa.SaralSewa.shared.dto.request.delete;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookingStatusRequest extends ModelBase {

    @NotNull(message = "Booking Status ID is required")
    private Integer bookingStatusId;
}
