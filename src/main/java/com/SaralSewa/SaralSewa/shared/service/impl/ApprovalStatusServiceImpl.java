package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ResponseUtil;
import com.SaralSewa.SaralSewa.shared.dto.request.action.approval.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListApprovalStatusResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewApprovalStatusResponse;
import com.SaralSewa.SaralSewa.shared.entity.ApprovalStatus;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.ApprovalStatusMapper;
import com.SaralSewa.SaralSewa.shared.repository.ApprovalStatusRepository;
import com.SaralSewa.SaralSewa.shared.service.ApprovalStatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ApprovalStatusServiceImpl implements ApprovalStatusService {

    private final ApprovalStatusRepository approvalStatusRepository;
    private final ApprovalStatusMapper approvalStatusMapper;

    @Override
    public ApiResponse<ViewApprovalStatusResponse> createStatus(CreateApprovalStatusRequest request) {
        if (approvalStatusRepository.existsByCode(request.getCode())) {
            throw new ApiException("Approval status code already exists.", HttpStatus.BAD_REQUEST);
        }
        ApprovalStatus status = approvalStatusMapper.create(request);
        ApprovalStatus savedStatus = approvalStatusRepository.save(status);
        return  ResponseUtil.getSuccessfulApiResponse(approvalStatusMapper.viewDetails(savedStatus), "Approval status created successfully.");
    }

    @Override
    public ApiResponse<ViewApprovalStatusResponse> updateStatus(UpdateApprovalStatusRequest request) {
        ApprovalStatus status = approvalStatusRepository.findById(request.getApprovalStatusId())
                .orElseThrow(() -> new ApiException("Approval status not found.", HttpStatus.NOT_FOUND));
        status = approvalStatusMapper.update(request, status);
        ApprovalStatus updatedStatus = approvalStatusRepository.save(status);
        return  ResponseUtil.getSuccessfulApiResponse(approvalStatusMapper.viewDetails(updatedStatus), "Approval status updated successfully.");
    }

    @Override
    public ApiResponse<ViewApprovalStatusResponse> getStatusById(GetApprovalStatusByIdRequest request) {
        ApprovalStatus status = approvalStatusRepository.findById(request.getApprovalStatusId())
                .orElseThrow(() -> new ApiException("Approval status not found.", HttpStatus.NOT_FOUND));
        return  ResponseUtil.getSuccessfulApiResponse(approvalStatusMapper.viewDetails(status), "Approval status retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewApprovalStatusResponse> getStatusByCode(GetApprovalStatusByCodeRequest request) {
        ApprovalStatus status = approvalStatusRepository.findByCode(request.getCode());
        if (status == null) {
            throw new ApiException("Approval status not found.", HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.getSuccessfulApiResponse(approvalStatusMapper.viewDetails(status), "Approval status retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListApprovalStatusResponse>> getAllStatuses(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<ApprovalStatus> statuses = approvalStatusRepository.findAll(pageable);
        Page<ListApprovalStatusResponse> response = statuses.map(approvalStatusMapper::entityToResponse);
        return  ResponseUtil.getSuccessfulApiResponse(PageResponse.from(response), "Approval statuses retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteStatus(DeleteApprovalStatusRequest request) {
        ApprovalStatus status = approvalStatusRepository.findById(request.getApprovalStatusId())
                .orElseThrow(() -> new ApiException("Approval status not found.", HttpStatus.NOT_FOUND));
        approvalStatusRepository.delete(status);
        return  ResponseUtil.getSuccessfulApiResponse(null, "Approval status deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteStatus(SoftDeleteApprovalStatusRequest request) {
        approvalStatusRepository.softDeleteById(request.getApprovalStatusId());
        return  ResponseUtil.getSuccessfulApiResponse(null, "Approval status soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateStatus(ActivateApprovalStatusRequest request) {
        ApprovalStatus status = approvalStatusRepository.findById(request.getApprovalStatusId())
                .orElseThrow(() -> new ApiException("Approval status not found.", HttpStatus.NOT_FOUND));
        status = approvalStatusMapper.activate(status);
        approvalStatusRepository.save(status);
        return  ResponseUtil.getSuccessfulApiResponse(null, "Approval status activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateStatus(DeactivateApprovalStatusRequest request) {
        ApprovalStatus status = approvalStatusRepository.findById(request.getApprovalStatusId())
                .orElseThrow(() -> new ApiException("Approval status not found.", HttpStatus.NOT_FOUND));
        status = approvalStatusMapper.deactivate(status);
        approvalStatusRepository.save(status);
        return  ResponseUtil.getSuccessfulApiResponse(null, "Approval status deactivated successfully.");
    }

    @Override
    public boolean existsByCode(String code) {
        return approvalStatusRepository.existsByCode(code);
    }
}
