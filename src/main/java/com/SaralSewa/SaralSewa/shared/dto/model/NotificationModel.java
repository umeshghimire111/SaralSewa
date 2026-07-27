package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationModel extends ModelBase {
    private Integer userId;
    private String title;
    private String message;
    private String notificationType;
    private Boolean isRead;
    private Boolean isActive;
    private Boolean isDeleted;
}
