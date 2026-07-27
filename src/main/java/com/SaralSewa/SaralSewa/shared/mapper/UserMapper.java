package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.response.view.*;

import com.SaralSewa.SaralSewa.shared.constant.RoleConstant;
import com.SaralSewa.SaralSewa.shared.constant.StatusConstant;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListUserResponse;
import com.SaralSewa.SaralSewa.shared.entity.Role;
import com.SaralSewa.SaralSewa.shared.entity.Status;
import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.repository.RoleRepository;
import com.SaralSewa.SaralSewa.shared.repository.StatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ViewUserResponse viewDetails(User user) {
        if (user == null) return null;

        ViewUserResponse response = new ViewUserResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setFullName(user.getFirstName() + (user.getLastName() != null ? " " + user.getLastName() : ""));
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setAddress(user.getAddress());
        response.setProfileImage(user.getProfileImage());
        response.setDescription(user.getDescription());
        response.setRoleName(user.getRole() != null ? user.getRole().getName() : null);
        response.setRoleCode(user.getRole() != null ? user.getRole().getCode() : null);
        response.setStatusName(user.getStatus() != null ? user.getStatus().getName() : null);
        response.setStatusCode(user.getStatus() != null ? user.getStatus().getCode() : null);
        response.setIsActive(user.getIsActive());
        response.setLastLoggedInTime(user.getLastLoggedInTime());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

    public abstract ListUserResponse entityToResponse(User user);

    public List<ListUserResponse> listUsers(List<User> users) {
        if (users == null) return null;
        return users.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public User create(CreateUserRequest request) {
        if (request == null) return null;

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setProfileImage(request.getProfileImage());
        user.setDescription(request.getDescription());

        Role defaultRole = roleRepository.findByCode(RoleConstant.CUSTOMER.getName());
        user.setRole(defaultRole);

        Status defaultStatus = statusRepository.findByCode(StatusConstant.ACTIVE.getName());
        user.setStatus(defaultStatus);

        user.setIsActive(true);
        user.setIsDeleted(false);
        user.setWrongPasswordAttemptCount(0);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }

    public User update(UpdateUserRequest request, User user) {
        if (request == null || user == null) return user;

        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }
        if (request.getProfileImage() != null) {
            user.setProfileImage(request.getProfileImage());
        }
        if (request.getDescription() != null) {
            user.setDescription(request.getDescription());
        }
        if (request.getRoleCode() != null) {
            Role role = roleRepository.findByCode(request.getRoleCode());
            user.setRole(role);
        }
        if (request.getStatusCode() != null) {
            Status status = statusRepository.findByCode(request.getStatusCode());
            user.setStatus(status);
        }
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    public User updateStatus(User user, String statusCode) {
        if (user == null || statusCode == null) return user;
        Status status = statusRepository.findByCode(statusCode);
        user.setStatus(status);
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    public User updateRole(User user, String roleCode) {
        if (user == null || roleCode == null) return user;
        Role role = roleRepository.findByCode(roleCode);
        user.setRole(role);
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    public User updateLastLogin(User user) {
        if (user == null) return user;
        user.setLastLoggedInTime(LocalDateTime.now());
        user.setWrongPasswordAttemptCount(0);
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    public User incrementFailedAttempts(User user) {
        if (user == null) return user;
        int attempts = user.getWrongPasswordAttemptCount() != null ? user.getWrongPasswordAttemptCount() : 0;
        user.setWrongPasswordAttemptCount(attempts + 1);
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    public User deactivate(User user) {
        if (user == null) return user;
        user.setIsActive(false);
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    public User activate(User user) {
        if (user == null) return user;
        user.setIsActive(true);
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    public User softDelete(User user) {
        if (user == null) return user;
        user.setIsDeleted(true);
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }
}