package com.SaralSewa.SaralSewa.shared.dto.response.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ViewBookingResponse {
    private String bookingCode;
    private Integer customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Integer providerId;
    private String providerName;
    private String providerProfession;
    private Integer slotId;
    private String slotDateTime;
    private String serviceDescription;
    private String customerAddress;
    private String bookingStatusName;
    private String bookingStatusCode;
    private BigDecimal totalAmount;
    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
