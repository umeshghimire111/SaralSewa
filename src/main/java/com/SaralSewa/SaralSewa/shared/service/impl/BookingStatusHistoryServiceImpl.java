package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.dto.request.action.history.GetHistoryByIdRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.history.GetHistoriesByBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.history.LogStatusChangeRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListBookingStatusHistoryResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewBookingStatusHistoryResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.entity.BookingStatusHistory;
import com.SaralSewa.SaralSewa.shared.entity.Status;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.BookingStatusHistoryMapper;
import com.SaralSewa.SaralSewa.shared.repository.BookingRepository;
import com.SaralSewa.SaralSewa.shared.repository.BookingStatusHistoryRepository;
import com.SaralSewa.SaralSewa.shared.repository.StatusRepository;
import com.SaralSewa.SaralSewa.shared.service.BookingStatusHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BookingStatusHistoryServiceImpl implements BookingStatusHistoryService {

    private final BookingStatusHistoryRepository bookingStatusHistoryRepository;
    private final BookingStatusHistoryMapper bookingStatusHistoryMapper;
    private final StatusRepository statusRepository;
    private final BookingRepository bookingRepository;

    @Override
    public ApiResponse<ViewBookingStatusHistoryResponse> getHistoryById(GetHistoryByIdRequest request) {
        BookingStatusHistory history = bookingStatusHistoryRepository.findById(request.getHistoryId())
                .orElseThrow(() -> new ApiException("History not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(bookingStatusHistoryMapper.viewDetails(history), "History retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListBookingStatusHistoryResponse>> getAllHistories(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<BookingStatusHistory> histories = bookingStatusHistoryRepository.findAll(pageable);
        Page<ListBookingStatusHistoryResponse> response = histories.map(bookingStatusHistoryMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Histories retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListBookingStatusHistoryResponse>> getHistoriesByBooking(GetHistoriesByBookingRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<BookingStatusHistory> histories = bookingStatusHistoryRepository.findByBookingId(request.getBookingId(), pageable);
        Page<ListBookingStatusHistoryResponse> response = histories.map(bookingStatusHistoryMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Booking histories retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> logStatusChange(Integer bookingId, String oldStatusCode, String newStatusCode, String remarks, Integer changedBy) {
        BookingStatusHistory history = new BookingStatusHistory();
        history.setBooking(bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ApiException("Booking not found.", HttpStatus.NOT_FOUND)));

        if (oldStatusCode != null) {
            Status oldStatus = statusRepository.findByCode(oldStatusCode);
            history.setOldStatus(oldStatus);
        }

        Status newStatus = statusRepository.findByCode(newStatusCode);
        if (newStatus == null) {
            throw new ApiException("New status not found: " + newStatusCode, HttpStatus.NOT_FOUND);
        }
        history.setNewStatus(newStatus);

        history.setRemarks(remarks);
        history.setChangedBy(changedBy);
        history.setCreatedAt(LocalDateTime.now());
        history.setUpdatedAt(LocalDateTime.now());
        history.setIsDeleted(false);

        bookingStatusHistoryRepository.save(history);
        return ApiResponse.success(null, "Status change logged successfully.");
    }
}
