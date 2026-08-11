package com.SaralSewa.SaralSewa.shared.service;


import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.UserDto;
import com.SaralSewa.SaralSewa.shared.dto.request.action.AuthenticateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.UserAuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserAuthenticationService {
    ApiResponse<?> register(UserDto userDto);
    ApiResponse<UserAuthenticationResponse> authenticate(AuthenticateUserRequest authenticateUserRequest, HttpServletResponse response);
    ApiResponse<?> refreshToken(HttpServletRequest request, HttpServletResponse response);
    ApiResponse<?> logout(HttpServletRequest request, HttpServletResponse response);

}
