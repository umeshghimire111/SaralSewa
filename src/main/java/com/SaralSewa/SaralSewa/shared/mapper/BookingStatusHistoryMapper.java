package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.response.BookingStatusHistoryResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewBookingStatusHistoryResponse;
import com.SaralSewa.SaralSewa.shared.entity.BookingStatusHistory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BookingStatusHistoryMapper {

    public ViewBookingStatusHistoryResponse viewDetails(BookingStatusHistory history) {
        if (history == null) return null;

        ViewBookingStatusHistoryResponse response = new ViewBookingStatusHistoryResponse();
        response.setBookingId(Long.valueOf(history.getBooking() != null ? history.getBooking().getId() : null));
        response.setBookingCode(history.getBooking() != null ? history.getBooking().getBookingCode() : null);
        response.setOldStatusName(history.getOldStatus() != null ? history.getOldStatus().getName() : null);
        response.setOldStatusCode(history.getOldStatus() != null ? history.getOldStatus().getCode() : null);
        response.setNewStatusName(history.getNewStatus() != null ? history.getNewStatus().getName() : null);
        response.setNewStatusCode(history.getNewStatus() != null ? history.getNewStatus().getCode() : null);
        response.setRemarks(history.getRemarks());
        response.setChangedBy(history.getChangedBy());
        response.setCreatedAt(history.getCreatedAt());

        return response;
    }

    public abstract BookingStatusHistoryResponse entityToResponse(BookingStatusHistory history);

    public List<BookingStatusHistoryResponse> listHistories(List<BookingStatusHistory> histories) {
        if (histories == null) return null;
        return histories.stream().map(this::entityToResponse).collect(Collectors.toList());
    }
}