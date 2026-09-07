package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.AuthenticateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.ResendOtpRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.VerifyOtpRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.LoginResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.UserAuthenticationResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface OTPAuthenticationService {

    ApiResponse<LoginResponse> sendOtp(AuthenticateUserRequest request, HttpServletResponse response);

    ApiResponse<UserAuthenticationResponse> verifyOtp(VerifyOtpRequest request, HttpServletResponse response);

    ApiResponse<LoginResponse> resendOtp(ResendOtpRequest request, HttpServletResponse response);

}
