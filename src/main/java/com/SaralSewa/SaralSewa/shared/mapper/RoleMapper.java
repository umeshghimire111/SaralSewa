package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.response.view.*;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListRoleResponse;
import com.SaralSewa.SaralSewa.shared.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class RoleMapper {

    public ViewRoleResponse viewDetails(Role role) {
        if (role == null) return null;

        ViewRoleResponse response = new ViewRoleResponse();
        response.setName(role.getName());
        response.setCode(role.getCode());
        response.setDescription(role.getDescription());
        response.setIsActive(role.getIsActive());
        response.setCreatedAt(role.getCreatedAt());
        response.setUpdatedAt(role.getUpdatedAt());

        return response;
    }

    public abstract ListRoleResponse entityToResponse(Role role);

    public List<ListRoleResponse> listRoles(List<Role> roles) {
        if (roles == null) return null;
        return roles.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public Role create(CreateRoleRequest request) {
        if (request == null) return null;

        Role role = new Role();
        role.setName(request.getName());
        role.setCode(request.getCode());
        role.setDescription(request.getDescription());
        role.setIsActive(true);
        role.setIsDeleted(false);
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());

        return role;
    }

    public Role update(UpdateRoleRequest request, Role role) {
        if (request == null || role == null) return role;

        if (request.getName() != null) {
            role.setName(request.getName());
        }
        if (request.getCode() != null) {
            role.setCode(request.getCode());
        }
        if (request.getDescription() != null) {
            role.setDescription(request.getDescription());
        }
        role.setUpdatedAt(LocalDateTime.now());
        return role;
    }

    public Role deactivate(Role role) {
        if (role == null) return role;
        role.setIsActive(false);
        role.setUpdatedAt(LocalDateTime.now());
        return role;
    }

    public Role activate(Role role) {
        if (role == null) return role;
        role.setIsActive(true);
        role.setUpdatedAt(LocalDateTime.now());
        return role;
    }

    public Role softDelete(Role role) {
        if (role == null) return role;
        role.setIsDeleted(true);
        role.setUpdatedAt(LocalDateTime.now());
        return role;
    }
}