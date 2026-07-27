package com.SaralSewa.SaralSewa.shared.dto.request.action.history;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogStatusChangeRequest extends ModelBase {

    @NotNull(message = "Booking ID is required")
    private Integer bookingId;

    private String oldStatusCode;

    @NotNull(message = "New status code is required")
    private String newStatusCode;

    private String remarks;

    @NotNull(message = "Changed by user ID is required")
    private Integer changedBy;
}
