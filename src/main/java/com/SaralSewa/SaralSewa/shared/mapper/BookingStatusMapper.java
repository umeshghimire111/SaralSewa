package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.response.view.*;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListBookingStatusResponse;
import com.SaralSewa.SaralSewa.shared.entity.BookingStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BookingStatusMapper {

    public ViewBookingStatusResponse viewDetails(BookingStatus status) {
        if (status == null) return null;

        ViewBookingStatusResponse response = new ViewBookingStatusResponse();
        response.setName(status.getName());
        response.setCode(status.getCode());
        response.setDescription(status.getDescription());
        response.setIsActive(status.getIsActive());
        status.getIsDefault();
        response.setCreatedAt(status.getCreatedAt());
        response.setUpdatedAt(status.getUpdatedAt());

        return response;
    }

    public abstract ListBookingStatusResponse entityToResponse(BookingStatus status);

    public List<ListBookingStatusResponse> listStatuses(List<BookingStatus> statuses) {
        if (statuses == null) return null;
        return statuses.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public BookingStatus create(CreateBookingStatusRequest request) {
        if (request == null) return null;

        BookingStatus status = new BookingStatus();
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

    public BookingStatus update(UpdateBookingStatusRequest request, BookingStatus status) {
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

    public BookingStatus deactivate(BookingStatus status) {
        if (status == null) return status;
        status.setIsActive(false);
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }

    public BookingStatus activate(BookingStatus status) {
        if (status == null) return status;
        status.setIsActive(true);
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }

    public BookingStatus softDelete(BookingStatus status) {
        if (status == null) return status;
        status.setIsDeleted(true);
        status.setUpdatedAt(LocalDateTime.now());
        return status;
    }
}