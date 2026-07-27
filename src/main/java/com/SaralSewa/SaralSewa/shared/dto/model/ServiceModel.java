package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceModel extends ModelBase {
    private String name;
    private String code;
    private String description;
    private Integer categoryId;
    private Integer estimatedDuration;
    private Double basePrice;
    private Boolean isActive;
    private Boolean isDeleted;
}
