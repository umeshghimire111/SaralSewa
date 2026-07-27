package com.SaralSewa.SaralSewa.shared.dto.response.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ViewProviderProfileResponse {
    private Integer providerId;
    private String providerName;
    private String providerProfession;
    private String bio;
    private Integer yearsOfExperience;
    private BigDecimal hourlyRate;
    private String city;
    private String district;
    private String profileImage;
    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
