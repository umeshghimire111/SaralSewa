package com.SaralSewa.SaralSewa.shared.dto.response.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ViewCustomerProfileResponse {
    private Integer userId;
    private String userFullName;
    private String userEmail;
    private String userPhone;
    private String address;
    private String savedAddresses;
    private String preferredPayment;
    private String preferredContact;
    private String notificationPreferences;
    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
