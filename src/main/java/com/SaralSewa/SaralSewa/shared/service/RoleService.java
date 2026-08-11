package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.role.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListRoleResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewRoleResponse;

public interface RoleService {

    ApiResponse<ViewRoleResponse> createRole(CreateRoleRequest request);

    ApiResponse<ViewRoleResponse> updateRole(UpdateRoleRequest request);

    ApiResponse<ViewRoleResponse> getRoleById(GetRoleByIdRequest request);

    ApiResponse<ViewRoleResponse> getRoleByCode(GetRoleByCodeRequest request);

    ApiResponse<PageResponse<ListRoleResponse>> getAllRoles(PageRequest pageRequest);

    ApiResponse<?> deleteRole(DeleteRoleRequest request);

    ApiResponse<?> softDeleteRole(SoftDeleteRoleRequest request);

    ApiResponse<?> activateRole(ActivateRoleRequest request);

    ApiResponse<?> deactivateRole(DeactivateRoleRequest request);

    boolean existsByCode(String code);
}
