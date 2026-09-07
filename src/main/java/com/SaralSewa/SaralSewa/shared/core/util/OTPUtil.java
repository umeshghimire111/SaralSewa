package com.SaralSewa.SaralSewa.shared.core.util;


import java.security.SecureRandom;
import java.time.LocalDateTime;

public class OTPUtil {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int OTP_EXPIRY_MINUTES = 5;

    public static String generateOTP() {
        int otp = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(otp);
    }

    public static LocalDateTime getExpiryTime() {
        return LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES);
    }

    public static int getOTPExpiryMinutes() {
        return OTP_EXPIRY_MINUTES;
    }
}