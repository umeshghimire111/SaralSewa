package com.SaralSewa.SaralSewa.shared.dto.request.update;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateServiceRequest extends ModelBase {

    private Integer serviceId;

    @Size(min = 2, max = 150, message = "Service name must be between 2 and 150 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Service code must be between 2 and 50 characters")
    private String code;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private Integer categoryId;

    @Positive(message = "Estimated duration must be positive")
    private Integer estimatedDuration;

    @Positive(message = "Base price must be positive")
    private BigDecimal basePrice;
}
