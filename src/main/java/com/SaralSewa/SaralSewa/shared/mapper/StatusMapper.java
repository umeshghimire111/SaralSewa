package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListStatusResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewStatusResponse;
import com.SaralSewa.SaralSewa.shared.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class StatusMapper {

    public ViewStatusResponse viewDetails(Status status) {
        if (status == null) return null;

        ViewStatusResponse response = new ViewStatusResponse();
        response.setName(status.getName());
        response.setCode(status.getCode());
        response.setDescription(status.getDescription());
        response.setIsActive(status.getIsActive());
        response.setCreatedAt(status.getCreatedAt());
        response.setUpdatedAt(status.getUpdatedAt());

        return response;
    }

    public abstract ListStatusResponse entityToResponse(Status status);

    public List<ListStatusResponse> listStatuses(List<Status> statuses) {
        if (statuses == null) return null;
        return statuses.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public Status create(CreateStatusRequest request) {
        if (request == null) return null;

        Status status = new Status();
        status.setName(request.getName());
        status.setCode(request.getCode());
        status.setDescription(request.getDescription());
        status.setIsActive(true);
        status.setIsDeleted(false);
        status.setCreatedAt(LocalDateTime.now());
        status.setUpdatedAt(LocalDateTime.now());

        return status;
    }

    public Status update(UpdateStatusRequest request, Status status) {
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

        if (request.getIsActive() != null) {
            status.setIsActive(request.getIsActive());
        }
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }

    public Status deactivate(Status status) {
        if (status == null) return status;
        status.setIsActive(false);
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }

    public Status activate(Status status) {
        if (status == null) return status;
        status.setIsActive(true);
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }

    public Status softDelete(Status status) {
        if (status == null) return status;
        status.setIsDeleted(true);
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }
}