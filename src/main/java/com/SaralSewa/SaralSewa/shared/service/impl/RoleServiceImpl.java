package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.role.SoftDeleteRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.role.ActivateRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.role.DeactivateRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.role.GetRoleByIdRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.role.GetRoleByCodeRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListRoleResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewRoleResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.entity.Role;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.RoleMapper;
import com.SaralSewa.SaralSewa.shared.repository.RoleRepository;
import com.SaralSewa.SaralSewa.shared.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public ApiResponse<ViewRoleResponse> createRole(CreateRoleRequest request) {
        if (roleRepository.existsByCode(request.getCode())) {
            throw new ApiException("Role code already exists.", HttpStatus.BAD_REQUEST);
        }
        Role role = roleMapper.create(request);
        Role savedRole = roleRepository.save(role);
        return ApiResponse.created(roleMapper.viewDetails(savedRole), "Role created successfully.");
    }

    @Override
    public ApiResponse<ViewRoleResponse> updateRole(UpdateRoleRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ApiException("Role not found.", HttpStatus.NOT_FOUND));
        role = roleMapper.update(request, role);
        Role updatedRole = roleRepository.save(role);
        return ApiResponse.success(roleMapper.viewDetails(updatedRole), "Role updated successfully.");
    }

    @Override
    public ApiResponse<ViewRoleResponse> getRoleById(GetRoleByIdRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ApiException("Role not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(roleMapper.viewDetails(role), "Role retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewRoleResponse> getRoleByCode(GetRoleByCodeRequest request) {
        Role role = roleRepository.findByCode(request.getCode());
        if (role == null) {
            throw new ApiException("Role not found.", HttpStatus.NOT_FOUND);
        }
        return ApiResponse.success(roleMapper.viewDetails(role), "Role retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListRoleResponse>> getAllRoles(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Role> roles = roleRepository.findAll(pageable);
        Page<ListRoleResponse> response = roles.map(roleMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Roles retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteRole(DeleteRoleRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ApiException("Role not found.", HttpStatus.NOT_FOUND));
        roleRepository.delete(role);
        return ApiResponse.success(null, "Role deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteRole(SoftDeleteRoleRequest request) {
        roleRepository.softDeleteById(request.getRoleId());
        return ApiResponse.success(null, "Role soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateRole(ActivateRoleRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ApiException("Role not found.", HttpStatus.NOT_FOUND));
        role = roleMapper.activate(role);
        roleRepository.save(role);
        return ApiResponse.success(null, "Role activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateRole(DeactivateRoleRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ApiException("Role not found.", HttpStatus.NOT_FOUND));
        role = roleMapper.deactivate(role);
        roleRepository.save(role);
        return ApiResponse.success(null, "Role deactivated successfully.");
    }

    @Override
    public boolean existsByCode(String code) {
        return roleRepository.existsByCode(code);
    }
}
