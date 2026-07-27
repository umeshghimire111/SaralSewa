package com.SaralSewa.SaralSewa.shared.dto.request.action.provider;



import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProviderProfileRequest extends ModelBase {

    @NotNull(message = "Provider ID is required")
    private Integer providerId;

    private String bio;
    private Integer yearsOfExperience;
    private BigDecimal hourlyRate;
    private String city;
    private String district;
    private String profileImage;
}
