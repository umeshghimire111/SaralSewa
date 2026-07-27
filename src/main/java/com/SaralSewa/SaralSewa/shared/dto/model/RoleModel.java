package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleModel extends ModelBase {
    private String name;
    private String code;
    private String description;
    private Boolean isActive;
    private Boolean isDeleted;
}
