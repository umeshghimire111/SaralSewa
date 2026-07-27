package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingStatusHistoryModel extends ModelBase {
    private Integer bookingId;
    private String oldStatusCode;
    private String newStatusCode;
    private String remarks;
    private Integer changedBy;
}
