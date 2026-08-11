package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.approval.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListApprovalStatusResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewApprovalStatusResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.ApprovalStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.APPROVAL_STATUSES)
@RequiredArgsConstructor
public class UserApprovalStatusController extends BaseController {

    private final ApprovalStatusService approvalStatusService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewApprovalStatusResponse> createStatus(@Valid @RequestBody CreateApprovalStatusRequest request) {
        return approvalStatusService.createStatus(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewApprovalStatusResponse> updateStatus(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateApprovalStatusRequest request) {
        request.setApprovalStatusId(id);
        return approvalStatusService.updateStatus(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewApprovalStatusResponse> getStatusById(@PathVariable Integer id) {
        GetApprovalStatusByIdRequest request = new GetApprovalStatusByIdRequest();
        request.setApprovalStatusId(id);
        return approvalStatusService.getStatusById(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CODE + ApiConstant.SLASH + "{code}")
    public ApiResponse<ViewApprovalStatusResponse> getStatusByCode(@PathVariable String code) {
        GetApprovalStatusByCodeRequest request = new GetApprovalStatusByCodeRequest();
        request.setCode(code);
        return approvalStatusService.getStatusByCode(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListApprovalStatusResponse>> getAllStatuses(@Valid PageRequest pageRequest) {
        return approvalStatusService.getAllStatuses(pageRequest);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteStatus(@PathVariable Integer id) {
        DeleteApprovalStatusRequest request = new DeleteApprovalStatusRequest();
        request.setApprovalStatusId(id);
        return approvalStatusService.deleteStatus(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteStatus(@PathVariable Integer id) {
        SoftDeleteApprovalStatusRequest request = new SoftDeleteApprovalStatusRequest();
        request.setApprovalStatusId(id);
        return approvalStatusService.softDeleteStatus(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateStatus(@PathVariable Integer id) {
        ActivateApprovalStatusRequest request = new ActivateApprovalStatusRequest();
        request.setApprovalStatusId(id);
        return approvalStatusService.activateStatus(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateStatus(@PathVariable Integer id) {
        DeactivateApprovalStatusRequest request = new DeactivateApprovalStatusRequest();
        request.setApprovalStatusId(id);
        return approvalStatusService.deactivateStatus(request);
    }
}
