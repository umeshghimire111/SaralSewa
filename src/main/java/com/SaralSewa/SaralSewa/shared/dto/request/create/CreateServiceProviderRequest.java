package com.SaralSewa.SaralSewa.shared.dto.request.create;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateServiceProviderRequest extends ModelBase {

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotBlank(message = "Profession is required")
    @Size(min = 2, max = 100, message = "Profession must be between 2 and 100 characters")
    private String profession;

    private Integer experienceYears;

    @NotBlank(message = "Approval status code is required")
    private String approvalStatusCode;
}
