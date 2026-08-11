package com.SaralSewa.SaralSewa.shared.dto.request.update;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProviderSkillRequest extends ModelBase {

    private Integer skillId;

    @Size(min = 2, max = 100, message = "Skill name must be between 2 and 100 characters")
    private String skillName;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;
}
