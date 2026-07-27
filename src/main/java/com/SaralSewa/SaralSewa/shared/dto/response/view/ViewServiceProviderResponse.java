package com.SaralSewa.SaralSewa.shared.dto.response.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ViewServiceProviderResponse {
    private Integer userId;
    private String userFullName;
    private String userEmail;
    private String userPhone;
    private String profession;
    private Integer experienceYears;
    private String approvalStatusName;
    private String approvalStatusCode;
    private BigDecimal averageRating;
    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
