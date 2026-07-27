package com.SaralSewa.SaralSewa.shared.dto.response.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ListBookingResponse {
    private String bookingCode;
    private String customerName;
    private String providerName;
    private String bookingStatusName;
    private BigDecimal totalAmount;
    private String bookingDate;
    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
