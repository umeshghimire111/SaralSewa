package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.security.JwtService;
import com.SaralSewa.SaralSewa.shared.core.service.EmailService;
import com.SaralSewa.SaralSewa.shared.core.util.OTPUtil;
import com.SaralSewa.SaralSewa.shared.core.util.UserTokenUtil;
import com.SaralSewa.SaralSewa.shared.dto.request.action.AuthenticateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.ResendOtpRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.VerifyOtpRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.LoginResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.UserAuthenticationResponse;
import com.SaralSewa.SaralSewa.shared.entity.OTPCode;
import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.entity.UserToken;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.repository.OTPRepository;
import com.SaralSewa.SaralSewa.shared.repository.UserRepository;
import com.SaralSewa.SaralSewa.shared.repository.UserTokenRepository;
import com.SaralSewa.SaralSewa.shared.service.OTPAuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class OTPAuthenticationServiceImpl implements OTPAuthenticationService {

    private final UserRepository userRepository;
    private final OTPRepository otpRepository;
    private final UserTokenRepository userTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    private static final int MAX_OTP_ATTEMPTS = 3;

    @Override
    @Transactional
    public ApiResponse<LoginResponse> sendOtp(AuthenticateUserRequest request, HttpServletResponse response) {
        log.info("OTP login request for email: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new ApiException("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }

        if (user.getAccountLockedUntil() != null &&
                user.getAccountLockedUntil().isAfter(LocalDateTime.now())) {
            throw new ApiException("Account is locked. Please try again later.", HttpStatus.FORBIDDEN);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            int attempts = user.getWrongPasswordAttemptCount() != null ? user.getWrongPasswordAttemptCount() : 0;
            attempts++;
            user.setWrongPasswordAttemptCount(attempts);

            if (attempts >= 5) {
                user.setAccountLockedUntil(LocalDateTime.now().plusMinutes(30));
            }
            userRepository.save(user);
            throw new ApiException("Invalid password", HttpStatus.UNAUTHORIZED);
        }

        if (!user.getIsActive() || user.getIsDeleted()) {
            throw new ApiException("Account is inactive or deleted", HttpStatus.FORBIDDEN);
        }

        user.setWrongPasswordAttemptCount(0);
        user.setAccountLockedUntil(null);
        userRepository.save(user);

        otpRepository.deleteByEmailAndIsVerifiedFalse(request.getEmail());

        String otpCode = OTPUtil.generateOTP();

        OTPCode otp = new OTPCode();
        otp.setEmail(request.getEmail());
        otp.setOtpCode(otpCode);
        otp.setExpiresAt(OTPUtil.getExpiryTime());
        otp.setCreatedAt(LocalDateTime.now());
        otp.setUpdatedAt(LocalDateTime.now());
        otpRepository.save(otp);

        emailService.sendLoginOtpEmail(user.getEmail(), user.getFirstName(), otpCode, OTPUtil.getOTPExpiryMinutes());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setOtpSent(true);
        loginResponse.setMessage("OTP sent to your registered email");
        loginResponse.setOtpExpiryMinutes(OTPUtil.getOTPExpiryMinutes());
        loginResponse.setRemainingAttempts(MAX_OTP_ATTEMPTS);

        return ApiResponse.success(loginResponse, "OTP sent successfully");
    }

    @Override
    @Transactional
    public ApiResponse<UserAuthenticationResponse> verifyOtp(VerifyOtpRequest request, HttpServletResponse response) {
        log.info("Verifying OTP for email: {}", request.getEmail());

        OTPCode otp = otpRepository.findByEmailAndOtpCodeAndIsVerifiedFalse(request.getEmail(), request.getOtpCode())
                .orElseThrow(() -> new ApiException("Invalid OTP", HttpStatus.BAD_REQUEST));

        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ApiException("OTP has expired. Please request a new one.", HttpStatus.BAD_REQUEST);
        }

        if (otp.getAttempts() >= MAX_OTP_ATTEMPTS) {
            throw new ApiException("Too many failed attempts. Please request a new OTP.", HttpStatus.BAD_REQUEST);
        }

        otp.setAttempts(otp.getAttempts() + 1);
        otpRepository.save(otp);
        otpRepository.markAsVerified(request.getEmail(), request.getOtpCode());

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        }

        if (request.getNewPassword() != null && !request.getNewPassword().isBlank()) {
            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                throw new ApiException("New password and confirm password do not match", HttpStatus.BAD_REQUEST);
            }
            String pwdPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
            if (request.getNewPassword().length() < 6 || !request.getNewPassword().matches(pwdPattern)) {
                throw new ApiException("Password must be at least 6 characters and contain one uppercase, one lowercase, one digit, and one special character", HttpStatus.BAD_REQUEST);
            }
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            user.setWrongPasswordAttemptCount(0);
            user.setAccountLockedUntil(null);
        }

        user.setLastLoggedInTime(LocalDateTime.now());
        userRepository.save(user);

        UserToken userToken = UserTokenUtil.saveToken(
                user,
                jwtService.generateAccessToken(user),
                userTokenRepository,
                jwtService.generateRefreshToken(user)
        );

        jwtService.setHttpOnlyCookie(response, "accessToken", userToken.getAccessToken(), 60 * 60 * 24);
        jwtService.setHttpOnlyCookie(response, "refreshToken", userToken.getRefreshToken(), 60 * 60 * 24 * 7);

        UserAuthenticationResponse authResponse = new UserAuthenticationResponse();
        authResponse.setAccessToken(userToken.getAccessToken());
        authResponse.setRefreshToken(userToken.getRefreshToken());

        return ApiResponse.success(authResponse, "OTP verified. Login successful.");
    }

    @Override
    @Transactional
    public ApiResponse<LoginResponse> resendOtp(ResendOtpRequest request, HttpServletResponse response) {
        log.info("Resending OTP for email: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        }

        otpRepository.deleteByEmailAndIsVerifiedFalse(request.getEmail());

        String otpCode = OTPUtil.generateOTP();

        OTPCode otp = new OTPCode();
        otp.setEmail(request.getEmail());
        otp.setOtpCode(otpCode);
        otp.setExpiresAt(OTPUtil.getExpiryTime());
        otp.setCreatedAt(LocalDateTime.now());
        otp.setUpdatedAt(LocalDateTime.now());
        otpRepository.save(otp);

        emailService.sendLoginOtpEmail(user.getEmail(), user.getFirstName(), otpCode, OTPUtil.getOTPExpiryMinutes());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setOtpSent(true);
        loginResponse.setMessage("New OTP sent to your registered email");
        loginResponse.setOtpExpiryMinutes(OTPUtil.getOTPExpiryMinutes());
        loginResponse.setRemainingAttempts(MAX_OTP_ATTEMPTS);

        return ApiResponse.success(loginResponse, "OTP resent successfully");
    }
}