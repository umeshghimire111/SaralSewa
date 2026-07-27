package com.SaralSewa.SaralSewa.shared.dto.request.action.notification;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkAllAsReadRequest extends ModelBase {

    @NotNull(message = "User ID is required")
    private Integer userId;
}
