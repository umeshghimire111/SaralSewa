package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderSkillModel extends ModelBase {
    private Integer providerId;
    private String skillName;
    private String description;
    private Boolean isActive;
    private Boolean isDeleted;
}
