package com.SaralSewa.SaralSewa.shared.dto.request.update;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerProfileRequest extends ModelBase {

    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    private String savedAddresses;

    @Size(max = 50, message = "Preferred payment must be less than 50 characters")
    private String preferredPayment;

    @Size(max = 50, message = "Preferred contact must be less than 50 characters")
    private String preferredContact;

    private String notificationPreferences;
}
