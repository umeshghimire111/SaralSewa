package com.SaralSewa.SaralSewa.shared.dto.request.delete;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookingStatusHistoryRequest extends ModelBase {

    @NotNull(message = "Booking Status History ID is required")
    private Integer historyId;
}