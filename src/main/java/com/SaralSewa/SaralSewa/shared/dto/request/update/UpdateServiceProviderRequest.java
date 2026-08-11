package com.SaralSewa.SaralSewa.shared.dto.request.update;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateServiceProviderRequest extends ModelBase {

    private Integer providerId;

    @Size(min = 2, max = 100, message = "Profession must be between 2 and 100 characters")
    private String profession;

    private Integer experienceYears;

    private String approvalStatusCode;
}
