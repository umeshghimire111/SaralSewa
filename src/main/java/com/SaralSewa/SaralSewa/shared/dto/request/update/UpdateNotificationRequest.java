package com.SaralSewa.SaralSewa.shared.dto.request.update;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNotificationRequest extends ModelBase {

    @Size(min = 2, max = 150, message = "Title must be between 2 and 150 characters")
    private String title;

    @Size(min = 2, max = 500, message = "Message must be between 2 and 500 characters")
    private String message;

    @Size(max = 50, message = "Notification type must be less than 50 characters")
    private String notificationType;

    private Boolean isRead;
}
