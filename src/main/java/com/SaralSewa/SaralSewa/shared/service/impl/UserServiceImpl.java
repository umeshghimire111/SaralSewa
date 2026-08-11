package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.user.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListUserResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewUserResponse;
import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.UserMapper;
import com.SaralSewa.SaralSewa.shared.repository.UserRepository;
import com.SaralSewa.SaralSewa.shared.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ApiResponse<ViewUserResponse> createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException("Email already registered.", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new ApiException("Phone number already registered.", HttpStatus.BAD_REQUEST);
        }
        User user = userMapper.create(request);
        User savedUser = userRepository.save(user);
        return ApiResponse.created(userMapper.viewDetails(savedUser), "User created successfully.");
    }

    @Override
    public ApiResponse<ViewUserResponse> updateUser(UpdateUserRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApiException("User not found.", HttpStatus.NOT_FOUND));
        user = userMapper.update(request, user);
        User updatedUser = userRepository.save(user);
        return ApiResponse.success(userMapper.viewDetails(updatedUser), "User updated successfully.");
    }

    @Override
    public ApiResponse<ViewUserResponse> getUserById(GetUserByIdRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApiException("User not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(userMapper.viewDetails(user), "User retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewUserResponse> getUserByEmail(GetUserByEmailRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new ApiException("User not found.", HttpStatus.NOT_FOUND);
        }
        return ApiResponse.success(userMapper.viewDetails(user), "User retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListUserResponse>> getAllUsers(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<User> users = userRepository.findAll(pageable);
        Page<ListUserResponse> response = users.map(userMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Users retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteUser(DeleteUserRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApiException("User not found.", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
        return ApiResponse.success(null, "User deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteUser(SoftDeleteUserRequest request) {
        userRepository.softDeleteById(request.getUserId());
        return ApiResponse.success(null, "User soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> restoreUser(RestoreUserRequest request) {
        userRepository.restoreById(request.getUserId());
        return ApiResponse.success(null, "User restored successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateUser(ActivateUserRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApiException("User not found.", HttpStatus.NOT_FOUND));
        user.setIsActive(true);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return ApiResponse.success(null, "User activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateUser(DeactivateUserRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApiException("User not found.", HttpStatus.NOT_FOUND));
        user.setIsActive(false);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return ApiResponse.success(null, "User deactivated successfully.");
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

}
