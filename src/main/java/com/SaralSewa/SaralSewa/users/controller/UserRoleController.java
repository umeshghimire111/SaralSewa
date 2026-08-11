package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteRoleRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.role.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListRoleResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewRoleResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.ROLES)
@RequiredArgsConstructor
public class UserRoleController extends BaseController {

    private final RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewRoleResponse> createRole(@Valid @RequestBody CreateRoleRequest request) {
        return roleService.createRole(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewRoleResponse> updateRole(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateRoleRequest request) {
        request.setRoleId(id);
        return roleService.updateRole(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewRoleResponse> getRoleById(@PathVariable Integer id) {
        GetRoleByIdRequest request = new GetRoleByIdRequest();
        request.setRoleId(id);
        return roleService.getRoleById(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CODE + ApiConstant.SLASH + "{code}")
    public ApiResponse<ViewRoleResponse> getRoleByCode(@PathVariable String code) {
        GetRoleByCodeRequest request = new GetRoleByCodeRequest();
        request.setCode(code);
        return roleService.getRoleByCode(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListRoleResponse>> getAllRoles(@Valid PageRequest pageRequest) {
        return roleService.getAllRoles(pageRequest);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteRole(@PathVariable Integer id) {
        DeleteRoleRequest request = new DeleteRoleRequest();
        request.setRoleId(id);
        return roleService.deleteRole(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteRole(@PathVariable Integer id) {
        SoftDeleteRoleRequest request = new SoftDeleteRoleRequest();
        request.setRoleId(id);
        return roleService.softDeleteRole(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateRole(@PathVariable Integer id) {
        ActivateRoleRequest request = new ActivateRoleRequest();
        request.setRoleId(id);
        return roleService.activateRole(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateRole(@PathVariable Integer id) {
        DeactivateRoleRequest request = new DeactivateRoleRequest();
        request.setRoleId(id);
        return roleService.deactivateRole(request);
    }
}
