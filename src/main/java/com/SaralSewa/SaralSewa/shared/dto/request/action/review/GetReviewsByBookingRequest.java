package com.SaralSewa.SaralSewa.shared.dto.request.action.review;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import com.SaralSewa.SaralSewa.shared.dto.common.PageRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetReviewsByBookingRequest extends ModelBase {

    @NotNull(message = "Booking ID is required")
    private Integer bookingId;

    private PageRequest pageRequest;
}
