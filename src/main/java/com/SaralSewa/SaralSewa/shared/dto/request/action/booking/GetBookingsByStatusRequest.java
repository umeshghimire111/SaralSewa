package com.SaralSewa.SaralSewa.shared.dto.request.action.booking;

import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBookingsByStatusRequest extends ModelBase {

    @NotBlank(message = "Status code is required")
    private String statusCode;
    private PageRequest pageRequest;
}
