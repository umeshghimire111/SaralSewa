package com.SaralSewa.SaralSewa.shared.dto.request.create;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingStatusRequest extends ModelBase {

    @NotBlank(message = "Status name is required")
    @Size(min = 2, max = 100, message = "Status name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Status code is required")
    @Size(min = 2, max = 50, message = "Status code must be between 2 and 50 characters")
    private String code;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private Boolean isDefault = false;
}
