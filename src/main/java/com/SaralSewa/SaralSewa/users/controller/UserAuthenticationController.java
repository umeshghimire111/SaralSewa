package com.SaralSewa.SaralSewa.users.controller;


import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.AuthenticateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.UserAuthenticationResponse;
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

    @PostMapping(ApiConstant.LOGIN)
    public ApiResponse<UserAuthenticationResponse> login(@RequestBody @Valid AuthenticateUserRequest authenticateUserRequest, HttpServletResponse response) {
        return userAuthenticationService.authenticate(authenticateUserRequest, response);
    }

    @PostMapping(ApiConstant.REFRESH_TOKEN+ApiConstant.SLASH+ApiConstant.CREATE)
    public ApiResponse<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return userAuthenticationService.refreshToken(request, response);
    }

    @PostMapping(ApiConstant.LOGOUT)
    public ApiResponse<?> logout(HttpServletRequest request, HttpServletResponse response) {
        return userAuthenticationService.logout(request, response);
    }

}