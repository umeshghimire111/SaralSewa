package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceProviderModel extends ModelBase {
    private Integer userId;
    private String profession;
    private Integer experienceYears;
    private String approvalStatusCode;
    private Double averageRating;
    private Boolean isActive;
    private Boolean isDeleted;
}
