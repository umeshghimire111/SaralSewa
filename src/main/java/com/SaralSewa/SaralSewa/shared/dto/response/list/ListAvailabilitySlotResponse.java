package com.SaralSewa.SaralSewa.shared.dto.response.list;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ListAvailabilitySlotResponse {
    private Integer providerId;
    private String providerName;
    private LocalDate availableDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean isBooked;
    private Boolean isActive;
}
