package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.response.view.*;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateApprovalStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListApprovalStatusResponse;
import com.SaralSewa.SaralSewa.shared.entity.ApprovalStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ApprovalStatusMapper {

    public ViewApprovalStatusResponse viewDetails(ApprovalStatus status) {
        if (status == null) return null;

        ViewApprovalStatusResponse response = new ViewApprovalStatusResponse();
        response.setName(status.getName());
        response.setCode(status.getCode());
        response.setDescription(status.getDescription());
        response.setIsDefault(status.getIsDefault());
        response.setIsActive(status.getIsActive());
        response.setCreatedAt(status.getCreatedAt());
        response.setUpdatedAt(status.getUpdatedAt());

        return response;
    }

    public abstract ListApprovalStatusResponse entityToResponse(ApprovalStatus status);

    public List<ListApprovalStatusResponse> listStatuses(List<ApprovalStatus> statuses) {
        if (statuses == null) return null;
        return statuses.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public ApprovalStatus create(CreateApprovalStatusRequest request) {
        if (request == null) return null;

        ApprovalStatus status = new ApprovalStatus();
        status.setName(request.getName());
        status.setCode(request.getCode());
        status.setDescription(request.getDescription());
        status.setIsDefault(request.getIsDefault() != null ? request.getIsDefault() : false);
        status.setIsActive(true);
        status.setIsDeleted(false);
        status.setCreatedAt(LocalDateTime.now());
        status.setUpdatedAt(LocalDateTime.now());

        return status;
    }

    public ApprovalStatus update(UpdateApprovalStatusRequest request, ApprovalStatus status) {
        if (request == null || status == null) return status;

        if (request.getName() != null) {
            status.setName(request.getName());
        }
        if (request.getCode() != null) {
            status.setCode(request.getCode());
        }
        if (request.getDescription() != null) {
            status.setDescription(request.getDescription());
        }
        if (request.getIsDefault() != null) {
            status.setIsDefault(request.getIsDefault());
        }
        if (request.getIsActive() != null) {
            status.setIsActive(request.getIsActive());
        }
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }

    public ApprovalStatus deactivate(ApprovalStatus status) {
        if (status == null) return status;
        status.setIsActive(false);
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }

    public ApprovalStatus activate(ApprovalStatus status) {
        if (status == null) return status;
        status.setIsActive(true);
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }

    public ApprovalStatus softDelete(ApprovalStatus status) {
        if (status == null) return status;
        status.setIsDeleted(true);
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }
}