package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AvailabilitySlotModel extends ModelBase {
    private Integer providerId;
    private LocalDate availableDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean isBooked;
    private Boolean isActive;
    private Boolean isDeleted;
}
