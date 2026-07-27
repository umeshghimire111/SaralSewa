package com.SaralSewa.SaralSewa.shared.dto.response.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ViewReviewResponse {
    private Integer bookingId;
    private String bookingCode;
    private Integer customerId;
    private String customerName;
    private String customerEmail;
    private Integer providerId;
    private String providerName;
    private String providerProfession;
    private Integer rating;
    private String reviewTitle;
    private String reviewMessage;
    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
