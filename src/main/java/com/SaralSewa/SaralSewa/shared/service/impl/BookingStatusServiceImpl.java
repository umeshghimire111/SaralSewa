package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.bookingstatus.SoftDeleteBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.bookingstatus.ActivateBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.bookingstatus.DeactivateBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.bookingstatus.GetBookingStatusByIdRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.bookingstatus.GetBookingStatusByCodeRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListBookingStatusResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewBookingStatusResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.entity.BookingStatus;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.BookingStatusMapper;
import com.SaralSewa.SaralSewa.shared.repository.BookingStatusRepository;
import com.SaralSewa.SaralSewa.shared.service.BookingStatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookingStatusServiceImpl implements BookingStatusService {

    private final BookingStatusRepository bookingStatusRepository;
    private final BookingStatusMapper bookingStatusMapper;

    @Override
    public ApiResponse<ViewBookingStatusResponse> createStatus(CreateBookingStatusRequest request) {
        if (bookingStatusRepository.existsByCode(request.getCode())) {
            throw new ApiException("Status code already exists.", HttpStatus.BAD_REQUEST);
        }
        BookingStatus status = bookingStatusMapper.create(request);
        BookingStatus savedStatus = bookingStatusRepository.save(status);
        return ApiResponse.created(bookingStatusMapper.viewDetails(savedStatus), "Booking status created successfully.");
    }

    @Override
    public ApiResponse<ViewBookingStatusResponse> updateStatus(UpdateBookingStatusRequest request) {
        BookingStatus status = bookingStatusRepository.findById(request.getBookingStatusId())
                .orElseThrow(() -> new ApiException("Booking status not found.", HttpStatus.NOT_FOUND));
        status = bookingStatusMapper.update(request, status);
        BookingStatus updatedStatus = bookingStatusRepository.save(status);
        return ApiResponse.success(bookingStatusMapper.viewDetails(updatedStatus), "Booking status updated successfully.");
    }

    @Override
    public ApiResponse<ViewBookingStatusResponse> getStatusById(GetBookingStatusByIdRequest request) {
        BookingStatus status = bookingStatusRepository.findById(request.getBookingStatusId())
                .orElseThrow(() -> new ApiException("Booking status not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(bookingStatusMapper.viewDetails(status), "Booking status retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewBookingStatusResponse> getStatusByCode(GetBookingStatusByCodeRequest request) {
        BookingStatus status = bookingStatusRepository.findByCode(request.getCode());
        if (status == null) {
            throw new ApiException("Booking status not found.", HttpStatus.NOT_FOUND);
        }
        return ApiResponse.success(bookingStatusMapper.viewDetails(status), "Booking status retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListBookingStatusResponse>> getAllStatuses(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<BookingStatus> statuses = bookingStatusRepository.findAll(pageable);
        Page<ListBookingStatusResponse> response = statuses.map(bookingStatusMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Booking statuses retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteStatus(DeleteBookingStatusRequest request) {
        BookingStatus status = bookingStatusRepository.findById(request.getBookingStatusId())
                .orElseThrow(() -> new ApiException("Booking status not found.", HttpStatus.NOT_FOUND));
        bookingStatusRepository.delete(status);
        return ApiResponse.success(null, "Booking status deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteStatus(SoftDeleteBookingStatusRequest request) {
        bookingStatusRepository.softDeleteById(request.getBookingStatusId());
        return ApiResponse.success(null, "Booking status soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateStatus(ActivateBookingStatusRequest request) {
        BookingStatus status = bookingStatusRepository.findById(request.getBookingStatusId())
                .orElseThrow(() -> new ApiException("Booking status not found.", HttpStatus.NOT_FOUND));
        status = bookingStatusMapper.activate(status);
        bookingStatusRepository.save(status);
        return ApiResponse.success(null, "Booking status activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateStatus(DeactivateBookingStatusRequest request) {
        BookingStatus status = bookingStatusRepository.findById(request.getBookingStatusId())
                .orElseThrow(() -> new ApiException("Booking status not found.", HttpStatus.NOT_FOUND));
        status = bookingStatusMapper.deactivate(status);
        bookingStatusRepository.save(status);
        return ApiResponse.success(null, "Booking status deactivated successfully.");
    }

    @Override
    public boolean existsByCode(String code) {
        return bookingStatusRepository.existsByCode(code);
    }
}
