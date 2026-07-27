package com.SaralSewa.SaralSewa.shared.dto.request.action.profile;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import com.SaralSewa.SaralSewa.shared.dto.common.PageRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProfilesByDistrictRequest extends ModelBase {

    @NotBlank(message = "District is required")
    private String district;

    private PageRequest pageRequest;
}
