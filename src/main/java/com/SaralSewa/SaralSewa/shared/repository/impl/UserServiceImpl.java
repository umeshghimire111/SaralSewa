package com.SaralSewa.SaralSewa.shared.repository.impl;


import com.SaralSewa.SaralSewa.shared.core.config.constant.RoleConstant;
import com.SaralSewa.SaralSewa.shared.core.config.constant.StatusConstant;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ResponseUtil;
import com.SaralSewa.SaralSewa.shared.entity.Role;
import com.SaralSewa.SaralSewa.shared.entity.Status;
import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.dto.UserDto;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.repository.RoleRepository;
import com.SaralSewa.SaralSewa.shared.repository.StatusRepository;
import com.SaralSewa.SaralSewa.shared.repository.UserRepository;
import com.SaralSewa.SaralSewa.shared.repository.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public ApiResponse<?> register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ApiException("Email is already registered", HttpStatus.CONFLICT);
        }

        Status defaultStatus = statusRepository.findByName(StatusConstant.ACTIVE.getName());
        if (defaultStatus == null) {
            return ResponseUtil.getFailureResponse("Default status not found");
        }

        Role defaultRole = roleRepository.findByName(RoleConstant.CUSTOMER.getName());
        if (defaultRole == null) {
            return ResponseUtil.getFailureResponse("Default role not found");
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setProfileImage(userDto.getProfileImage());
        user.setRole(defaultRole);
        user.setStatus(defaultStatus);
        user.setWrongPasswordAttemptCount(0);
        user.setIsActive(true);
        user.setIsDeleted(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());


        userRepository.save(user);

        return ResponseUtil.getSuccessfulApiResponse("User registered successfully");
    }
}