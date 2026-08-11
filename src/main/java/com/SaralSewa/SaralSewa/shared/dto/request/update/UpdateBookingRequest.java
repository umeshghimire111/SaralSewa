package com.SaralSewa.SaralSewa.shared.dto.request.update;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateBookingRequest extends ModelBase {

    private Integer bookingId;

    private Integer slotId;

    @Future(message = "Booking date must be in the future")
    private LocalDateTime bookingDate;

    @Size(max = 500, message = "Service description must be less than 500 characters")
    private String serviceDescription;

    @Size(max = 255, message = "Customer address must be less than 255 characters")
    private String customerAddress;

    @Positive(message = "Total amount must be positive")
    private BigDecimal totalAmount;

    private String bookingStatusCode;
}
