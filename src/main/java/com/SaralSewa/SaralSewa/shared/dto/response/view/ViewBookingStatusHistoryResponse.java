package com.SaralSewa.SaralSewa.shared.dto.response.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ViewBookingStatusHistoryResponse {
    private Integer bookingId;
    private String bookingCode;
    private String oldStatusName;
    private String oldStatusCode;
    private String newStatusName;
    private String newStatusCode;
    private String remarks;
    private Integer changedBy;
    private String changedByName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
