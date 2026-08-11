package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.status.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListStatusResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewStatusResponse;

public interface StatusService {

    ApiResponse<ViewStatusResponse> createStatus(CreateStatusRequest request);

    ApiResponse<ViewStatusResponse> updateStatus(UpdateStatusRequest request);

    ApiResponse<ViewStatusResponse> getStatusById(GetStatusByIdRequest request);

    ApiResponse<ViewStatusResponse> getStatusByCode(GetStatusByCodeRequest request);

    ApiResponse<PageResponse<ListStatusResponse>> getAllStatuses(PageRequest pageRequest);

    ApiResponse<?> deleteStatus(DeleteStatusRequest request);

    ApiResponse<?> softDeleteStatus(SoftDeleteStatusRequest request);

    ApiResponse<?> activateStatus(ActivateStatusRequest request);

    ApiResponse<?> deactivateStatus(DeactivateStatusRequest request);

    boolean existsByCode(String code);
}
