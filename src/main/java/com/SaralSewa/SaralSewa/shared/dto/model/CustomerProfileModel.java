package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerProfileModel extends ModelBase {
    private Integer userId;
    private String address;
    private String savedAddresses;
    private String preferredPayment;
    private String preferredContact;
    private String notificationPreferences;
    private Boolean isActive;
    private Boolean isDeleted;
}
