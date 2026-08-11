package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.approval.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListApprovalStatusResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewApprovalStatusResponse;

public interface ApprovalStatusService {

    ApiResponse<ViewApprovalStatusResponse> createStatus(CreateApprovalStatusRequest request);

    ApiResponse<ViewApprovalStatusResponse> updateStatus(UpdateApprovalStatusRequest request);

    ApiResponse<ViewApprovalStatusResponse> getStatusById(GetApprovalStatusByIdRequest request);

    ApiResponse<ViewApprovalStatusResponse> getStatusByCode(GetApprovalStatusByCodeRequest request);

    ApiResponse<PageResponse<ListApprovalStatusResponse>> getAllStatuses(PageRequest pageRequest);

    ApiResponse<?> deleteStatus(DeleteApprovalStatusRequest request);

    ApiResponse<?> softDeleteStatus(SoftDeleteApprovalStatusRequest request);

    ApiResponse<?> activateStatus(ActivateApprovalStatusRequest request);

    ApiResponse<?> deactivateStatus(DeactivateApprovalStatusRequest request);

    boolean existsByCode(String code);
}
