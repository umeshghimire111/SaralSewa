package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.status.SoftDeleteStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.status.ActivateStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.status.DeactivateStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.status.GetStatusByIdRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.status.GetStatusByCodeRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListStatusResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewStatusResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.entity.Status;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.StatusMapper;
import com.SaralSewa.SaralSewa.shared.repository.StatusRepository;
import com.SaralSewa.SaralSewa.shared.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    @Override
    public ApiResponse<ViewStatusResponse> createStatus(CreateStatusRequest request) {
        if (statusRepository.existsByCode(request.getCode())) {
            throw new ApiException("Status code already exists.", HttpStatus.BAD_REQUEST);
        }
        Status status = statusMapper.create(request);
        Status savedStatus = statusRepository.save(status);
        return ApiResponse.created(statusMapper.viewDetails(savedStatus), "Status created successfully.");
    }

    @Override
    public ApiResponse<ViewStatusResponse> updateStatus(UpdateStatusRequest request) {
        Status status = statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new ApiException("Status not found.", HttpStatus.NOT_FOUND));
        status = statusMapper.update(request, status);
        Status updatedStatus = statusRepository.save(status);
        return ApiResponse.success(statusMapper.viewDetails(updatedStatus), "Status updated successfully.");
    }

    @Override
    public ApiResponse<ViewStatusResponse> getStatusById(GetStatusByIdRequest request) {
        Status status = statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new ApiException("Status not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(statusMapper.viewDetails(status), "Status retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewStatusResponse> getStatusByCode(GetStatusByCodeRequest request) {
        Status status = statusRepository.findByCode(request.getCode());
        if (status == null) {
            throw new ApiException("Status not found.", HttpStatus.NOT_FOUND);
        }
        return ApiResponse.success(statusMapper.viewDetails(status), "Status retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListStatusResponse>> getAllStatuses(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Status> statuses = statusRepository.findAll(pageable);
        Page<ListStatusResponse> response = statuses.map(statusMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Statuses retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteStatus(DeleteStatusRequest request) {
        Status status = statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new ApiException("Status not found.", HttpStatus.NOT_FOUND));
        statusRepository.delete(status);
        return ApiResponse.success(null, "Status deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteStatus(SoftDeleteStatusRequest request) {
        statusRepository.softDeleteById(request.getStatusId());
        return ApiResponse.success(null, "Status soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateStatus(ActivateStatusRequest request) {
        Status status = statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new ApiException("Status not found.", HttpStatus.NOT_FOUND));
        status = statusMapper.activate(status);
        statusRepository.save(status);
        return ApiResponse.success(null, "Status activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateStatus(DeactivateStatusRequest request) {
        Status status = statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new ApiException("Status not found.", HttpStatus.NOT_FOUND));
        status = statusMapper.deactivate(status);
        statusRepository.save(status);
        return ApiResponse.success(null, "Status deactivated successfully.");
    }

    @Override
    public boolean existsByCode(String code) {
        return statusRepository.existsByCode(code);
    }
}
