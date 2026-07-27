package com.SaralSewa.SaralSewa.shared.dto.request.action.bookingstatus;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBookingStatusByCodeRequest extends ModelBase {

    @NotBlank(message = "Status code is required")
    private String code;
}
