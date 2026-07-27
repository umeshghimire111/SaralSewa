package com.SaralSewa.SaralSewa.shared.dto.request.action.slot;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSlotByIdRequest extends ModelBase {

    @NotNull(message = "Slot ID is required")
    private Integer slotId;
}
