package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingModel extends ModelBase {
    private String bookingCode;
    private Integer customerId;
    private Integer providerId;
    private Integer slotId;
    private String serviceDescription;
    private String customerAddress;
    private String bookingStatusCode;
    private Double totalAmount;
    private Boolean isActive;
    private Boolean isDeleted;
}
