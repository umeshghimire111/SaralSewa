package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.constant.RoleConstant;
import com.SaralSewa.SaralSewa.shared.constant.StatusConstant;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ResponseUtil;
import com.SaralSewa.SaralSewa.shared.core.model.UserPrincipal;
import com.SaralSewa.SaralSewa.shared.core.security.JwtService;
import com.SaralSewa.SaralSewa.shared.core.util.UserTokenUtil;
import com.SaralSewa.SaralSewa.shared.dto.UserDto;
import com.SaralSewa.SaralSewa.shared.dto.request.action.AuthenticateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.user.*;
import com.SaralSewa.SaralSewa.shared.dto.response.UserAuthenticationResponse;
import com.SaralSewa.SaralSewa.shared.entity.Role;
import com.SaralSewa.SaralSewa.shared.entity.Status;
import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.entity.UserToken;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.repository.RoleRepository;
import com.SaralSewa.SaralSewa.shared.repository.StatusRepository;
import com.SaralSewa.SaralSewa.shared.repository.UserRepository;
import com.SaralSewa.SaralSewa.shared.repository.UserTokenRepository;
import com.SaralSewa.SaralSewa.shared.service.UserAuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;


@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements UserAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final StatusRepository statusRepository;
    private final UserTokenRepository userTokenRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ApiResponse<?> register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ApiException("Email is already registered", HttpStatus.CONFLICT);
        }

        Status defaultStatus = statusRepository.findByName(StatusConstant.ACTIVE.getName());
        if (defaultStatus == null) {
            return ResponseUtil.getFailureResponse("Default status not found");
        }

        Role selectedRole;
        if ("PROVIDER".equalsIgnoreCase(userDto.getRole())) {
            selectedRole = roleRepository.findByName(RoleConstant.PROVIDER.getName());
            if (selectedRole == null) {
                return ResponseUtil.getFailureResponse("Provider role not found");
            }
        } else {
            selectedRole = roleRepository.findByName(RoleConstant.CUSTOMER.getName());
            if (selectedRole == null) {
                return ResponseUtil.getFailureResponse("Customer role not found");
            }
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setProfileImage(userDto.getProfileImage());
        user.setRole(selectedRole);
        user.setStatus(defaultStatus);
        user.setWrongPasswordAttemptCount(0);
        user.setIsActive(true);
        user.setIsDeleted(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseUtil.getSuccessfulApiResponse("User registered successfully as: " + userDto.getRole());
    }

    @Override
    @Transactional
    public ApiResponse<UserAuthenticationResponse> authenticate(AuthenticateUserRequest authenticateUserRequest, HttpServletResponse response) {
        User user = userRepository.findByEmail(authenticateUserRequest.getEmail());
        if (user == null || user.getStatus().equals(statusRepository.findByName(StatusConstant.DELETED.getName()))) {
            throw new ApiException("The account doesn't exist.", HttpStatus.NOT_FOUND);
        }
        if (user.getStatus().equals(statusRepository.findByName(StatusConstant.BLOCKED.getName()))) {
            throw new ApiException("The user is currently blocked. Please contact support.", HttpStatus.UNAUTHORIZED);
        }
        if (user.getStatus().equals(statusRepository.findByName(StatusConstant.ACTIVE.getName()))) {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticateUserRequest.getEmail(), authenticateUserRequest.getPassword()));
                authentication.isAuthenticated();
                if (authentication.isAuthenticated()) {
                    UserToken userToken = UserTokenUtil.saveToken(user,
                            jwtService.generateAccessToken(user),
                            userTokenRepository,
                            jwtService.generateRefreshToken(user));
                    jwtService.setHttpOnlyCookie(response,"accessToken",userToken.getAccessToken(),60*60*24);
                    jwtService.setHttpOnlyCookie(response,"refreshToken",userToken.getRefreshToken(),60*60*24*7);
                    userRepository.updateLastLoggedInTime(authenticateUserRequest.getEmail(), LocalDateTime.now());
                    userRepository.updateWrongPasswordAttemptCount(authenticateUserRequest.getEmail(),   0);

                    UserAuthenticationResponse authResponse =new UserAuthenticationResponse();
                    authResponse.setAccessToken(userToken.getAccessToken());
                    authResponse.setRefreshToken(userToken.getRefreshToken());

                    return ResponseUtil.getSuccessfulApiResponse(authResponse,"You have successfully logged in.");
                }
            } catch (BadCredentialsException e) {
                userRepository.updateWrongPasswordAttemptCount(user.getEmail(), user.getWrongPasswordAttemptCount() + 1);
                throw new ApiException("The password you entered is incorrect.", HttpStatus.UNAUTHORIZED);
            }
        }
        throw new ApiException("This account is awaiting verification. Please verify to continue.", HttpStatus.FORBIDDEN);
    }

    @Override
    public ApiResponse<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }
        if (refreshToken == null) {
            throw new ApiException("The refresh token is missing. Please log in again to continue.", HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.findByEmail(jwtService.extractEmail(refreshToken));
        if (user == null) {
            throw new ApiException("The refresh token is invalid. Please log in again to continue.", HttpStatus.UNAUTHORIZED);
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);
        boolean isRefreshTokenValid = jwtService.validateRefreshToken(refreshToken, userPrincipal);
        if (isRefreshTokenValid) {
            UserToken token = UserTokenUtil.saveToken(user,
                    jwtService.generateAccessToken(user),
                    userTokenRepository,
                    jwtService.generateRefreshToken(user));
            jwtService.setHttpOnlyCookie(response,"accessToken",token.getAccessToken(),60*60*24);
            jwtService.setHttpOnlyCookie(response,"refreshToken",token.getRefreshToken(),60*60*24*7);
            UserTokenUtil.invalidateToken(refreshToken, userTokenRepository::findByRefreshToken, userTokenRepository);
            return ResponseUtil.getSuccessfulApiResponse("Tokens refreshed successfully.");
        }
        throw new ApiException("The refresh token is invalid. Please log in again to continue.", HttpStatus.UNAUTHORIZED);
    }
    @Override
    public ApiResponse<?> logout(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("accessToken".equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                    break;
                }
            }
        }
        UserToken storedToken = userTokenRepository.findByAccessTokenAndLoggedOutFalse(accessToken).orElse(null);
        if (storedToken != null) {
            storedToken.setLoggedOut(true);
            userTokenRepository.save(storedToken);
            jwtService.clearCookie("accessToken", response);
            jwtService.clearCookie("refreshToken", response);
            SecurityContextHolder.clearContext();
            return ResponseUtil.getSuccessfulApiResponse("You have been successfully logged out.");
        }
        throw new ApiException("Logout failed. An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
