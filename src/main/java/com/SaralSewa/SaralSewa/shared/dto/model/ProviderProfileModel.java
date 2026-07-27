package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProviderProfileModel extends ModelBase {
    private Integer providerId;
    private String bio;
    private Integer yearsOfExperience;
    private BigDecimal hourlyRate;
    private String city;
    private String district;
    private String profileImage;
    private Boolean isActive;
    private Boolean isDeleted;
}
