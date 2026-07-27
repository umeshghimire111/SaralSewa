package com.SaralSewa.SaralSewa.shared.dto.request.action.notification;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoftDeleteNotificationRequest extends ModelBase {

    @NotNull(message = "Notification ID is required")
    private Integer notificationId;
}
