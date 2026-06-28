package com.SaralSewa.SaralSewa.users.controller;


import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.UserDto;
import com.SaralSewa.SaralSewa.shared.repository.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ApiConstant.API)
@AllArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping(ApiConstant.REGISTER)
    public ApiResponse<?> register(@RequestBody @Valid UserDto userDto) {

        return userService.register(userDto);

    }
}

