package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.status.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListStatusResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewStatusResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.StatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.STATUSES)
@RequiredArgsConstructor
public class UserStatusController extends BaseController {

    private final StatusService statusService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewStatusResponse> createStatus(@Valid @RequestBody CreateStatusRequest request) {
        return statusService.createStatus(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewStatusResponse> updateStatus(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateStatusRequest request) {
        request.setStatusId(id);
        return statusService.updateStatus(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewStatusResponse> getStatusById(@PathVariable Integer id) {
        GetStatusByIdRequest request = new GetStatusByIdRequest();
        request.setStatusId(id);
        return statusService.getStatusById(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CODE + ApiConstant.SLASH + "{code}")
    public ApiResponse<ViewStatusResponse> getStatusByCode(@PathVariable String code) {
        GetStatusByCodeRequest request = new GetStatusByCodeRequest();
        request.setCode(code);
        return statusService.getStatusByCode(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListStatusResponse>> getAllStatuses(@Valid PageRequest pageRequest) {
        return statusService.getAllStatuses(pageRequest);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteStatus(@PathVariable Integer id) {
        DeleteStatusRequest request = new DeleteStatusRequest();
        request.setStatusId(id);
        return statusService.deleteStatus(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteStatus(@PathVariable Integer id) {
        SoftDeleteStatusRequest request = new SoftDeleteStatusRequest();
        request.setStatusId(id);
        return statusService.softDeleteStatus(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateStatus(@PathVariable Integer id) {
        ActivateStatusRequest request = new ActivateStatusRequest();
        request.setStatusId(id);
        return statusService.activateStatus(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateStatus(@PathVariable Integer id) {
        DeactivateStatusRequest request = new DeactivateStatusRequest();
        request.setStatusId(id);
        return statusService.deactivateStatus(request);
    }
}
