package com.SaralSewa.SaralSewa.shared.dto.request.action.skill;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeactivateSkillRequest extends ModelBase {

    @NotNull(message = "Skill ID is required")
    private Integer skillId;
}
