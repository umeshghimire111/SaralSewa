package com.SaralSewa.SaralSewa.shared.dto.request.update;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class UpdateAvailabilitySlotRequest extends ModelBase {

    @Future(message = "Available date must be in the future")
    private LocalDate availableDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Boolean isBooked;
}
