package com.SaralSewa.SaralSewa.users.controller;


import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.AuthenticateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.ResendOtpRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.VerifyOtpRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.LoginResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.UserAuthenticationResponse;
import com.SaralSewa.SaralSewa.shared.service.OTPAuthenticationService;
import com.SaralSewa.SaralSewa.shared.service.UserAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.AUTH)
@RestController
@AllArgsConstructor
public class UserAuthenticationController extends BaseController {
    private final UserAuthenticationService userAuthenticationService;
    private final OTPAuthenticationService otpAuthenticationService;

    @PostMapping(ApiConstant.LOGIN)
    public ApiResponse<UserAuthenticationResponse> login(@RequestBody @Valid AuthenticateUserRequest request, HttpServletResponse response) {
        return userAuthenticationService.authenticate(request, response);
    }

    @PostMapping(ApiConstant.FORGET_PASSWORD)
    public ApiResponse<LoginResponse> forgotPassword(@RequestBody @Valid ResendOtpRequest request, HttpServletResponse response) {

        return otpAuthenticationService.resendOtp(request, response);
    }

    @PostMapping(ApiConstant.FORGET_PASSWORD + ApiConstant.SLASH + ApiConstant.VERIFY_OTP)
    public ApiResponse<UserAuthenticationResponse> verifyForgotPasswordOtp(@RequestBody @Valid VerifyOtpRequest request, HttpServletResponse response) {

        return otpAuthenticationService.verifyOtp(request, response);
    }

    @PostMapping(ApiConstant.FORGET_PASSWORD + ApiConstant.SLASH + ApiConstant.RESEND_OTP)
    public ApiResponse<LoginResponse> resendForgotPasswordOtp(@RequestBody @Valid ResendOtpRequest request, HttpServletResponse response) {

        return otpAuthenticationService.resendOtp(request, response);
    }

    @PostMapping(ApiConstant.RESET_PASSWORD)
    public ApiResponse<UserAuthenticationResponse> resetPassword(@RequestBody @Valid VerifyOtpRequest request, HttpServletResponse response) {

        return otpAuthenticationService.verifyOtp(request, response);
    }

    @PostMapping(ApiConstant.NEW_PASSWORD)
    public ApiResponse<UserAuthenticationResponse> newPassword(@RequestBody @Valid VerifyOtpRequest request, HttpServletResponse response) {

        return otpAuthenticationService.verifyOtp(request, response);
    }

    @PostMapping(ApiConstant.REFRESH_TOKEN + ApiConstant.SLASH + ApiConstant.CREATE)
    public ApiResponse<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {

        return userAuthenticationService.refreshToken(request, response);
    }

    @PostMapping(ApiConstant.LOGOUT)
    public ApiResponse<?> logout(HttpServletRequest request, HttpServletResponse response) {

        return userAuthenticationService.logout(request, response);
    }
}