package com.SaralSewa.SaralSewa.shared.dto.response.list;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ListProviderProfileResponse {
    private Integer providerId;
    private String providerName;
    private String profession;
    private String city;
    private String district;
    private BigDecimal hourlyRate;
    private Boolean isActive;
}
