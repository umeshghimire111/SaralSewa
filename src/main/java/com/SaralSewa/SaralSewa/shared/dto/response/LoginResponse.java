package com.SaralSewa.SaralSewa.shared.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private boolean otpSent;
    private String message;
    private int otpExpiryMinutes;
    private int remainingAttempts;
}