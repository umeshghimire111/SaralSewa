package com.SaralSewa.SaralSewa.shared.dto.request.create;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateBookingRequest extends ModelBase {

    @NotNull(message = "Customer ID is required")
    private Integer customerId;

    @NotNull(message = "Provider ID is required")
    private Integer providerId;

    @NotNull(message = "Slot ID is required")
    private Integer slotId;

    @NotBlank(message = "Booking code is required")
    @Size(min = 2, max = 50, message = "Booking code must be between 2 and 50 characters")
    private String bookingCode;

    @NotNull(message = "Booking date is required")
    @Future(message = "Booking date must be in the future")
    private LocalDateTime bookingDate;

    @Size(max = 500, message = "Service description must be less than 500 characters")
    private String serviceDescription;

    @Size(max = 255, message = "Customer address must be less than 255 characters")
    private String customerAddress;

    @Positive(message = "Total amount must be positive")
    private BigDecimal totalAmount;

    @NotBlank(message = "Booking status code is required")
    private String bookingStatusCode;
}
