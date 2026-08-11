package com.SaralSewa.SaralSewa.shared.dto.request.update;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookingStatusRequest extends ModelBase {

    private Integer bookingStatusId;

    @Size(min = 2, max = 100, message = "Status name must be between 2 and 100 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Status code must be between 2 and 50 characters")
    private String code;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private Boolean isDefault;

    private Boolean isActive;
}
