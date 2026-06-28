package com.SaralSewa.SaralSewa.shared.repository.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.UserDto;

public interface UserService {
    ApiResponse<?> register(UserDto userDto);


}