package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.booking.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListBookingResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewBookingResponse;
import com.SaralSewa.SaralSewa.shared.entity.Booking;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.BookingMapper;
import com.SaralSewa.SaralSewa.shared.repository.BookingRepository;
import com.SaralSewa.SaralSewa.shared.service.BookingService;
import com.SaralSewa.SaralSewa.shared.service.BookingStatusHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final BookingStatusHistoryService bookingStatusHistoryService;

    @Override
    public ApiResponse<ViewBookingResponse> createBooking(CreateBookingRequest request) {
        if (bookingRepository.existsByBookingCode(request.getBookingCode())) {
            throw new ApiException("Booking code already exists.", HttpStatus.BAD_REQUEST);
        }
        Booking booking = bookingMapper.create(request);
        Booking savedBooking = bookingRepository.save(booking);
        bookingStatusHistoryService.logStatusChange(
                savedBooking.getId(),
                null,
                savedBooking.getBookingStatus().getCode(),
                "Booking created",
                savedBooking.getCustomer().getId()
        );
        return ApiResponse.created(bookingMapper.viewDetails(savedBooking), "Booking created successfully.");
    }

    @Override
    public ApiResponse<ViewBookingResponse> updateBooking(UpdateBookingRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ApiException("Booking not found.", HttpStatus.NOT_FOUND));
        String oldStatus = booking.getBookingStatus() != null ? booking.getBookingStatus().getCode() : null;
        booking = bookingMapper.update(request, booking);
        Booking updatedBooking = bookingRepository.save(booking);
        if (request.getBookingStatusCode() != null && !request.getBookingStatusCode().equals(oldStatus)) {
            bookingStatusHistoryService.logStatusChange(
                    updatedBooking.getId(),
                    oldStatus,
                    updatedBooking.getBookingStatus().getCode(),
                    "Booking status updated",
                    updatedBooking.getCustomer().getId()
            );
        }
        return ApiResponse.success(bookingMapper.viewDetails(updatedBooking), "Booking updated successfully.");
    }

    @Override
    public ApiResponse<ViewBookingResponse> getBookingById(GetBookingByIdRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ApiException("Booking not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(bookingMapper.viewDetails(booking), "Booking retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewBookingResponse> getBookingByCode(GetBookingByCodeRequest request) {
        Booking booking = bookingRepository.findByBookingCode(request.getBookingCode());
        if (booking == null) {
            throw new ApiException("Booking not found.", HttpStatus.NOT_FOUND);
        }
        return ApiResponse.success(bookingMapper.viewDetails(booking), "Booking retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListBookingResponse>> getAllBookings(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Booking> bookings = bookingRepository.findAll(pageable);
        Page<ListBookingResponse> response = bookings.map(bookingMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Bookings retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListBookingResponse>> getBookingsByCustomer(GetBookingsByCustomerRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<Booking> bookings = bookingRepository.findByCustomerId(request.getCustomerId(), pageable);
        Page<ListBookingResponse> response = bookings.map(bookingMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Customer bookings retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListBookingResponse>> getBookingsByProvider(GetBookingsByProviderRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<Booking> bookings = bookingRepository.findByProviderId(request.getProviderId(), pageable);
        Page<ListBookingResponse> response = bookings.map(bookingMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Provider bookings retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListBookingResponse>> getBookingsByStatus(GetBookingsByStatusRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<Booking> bookings = bookingRepository.findByBookingStatusCode(request.getStatusCode(), pageable);
        Page<ListBookingResponse> response = bookings.map(bookingMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Bookings by status retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteBooking(DeleteBookingRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ApiException("Booking not found.", HttpStatus.NOT_FOUND));
        bookingRepository.delete(booking);
        return ApiResponse.success(null, "Booking deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteBooking(SoftDeleteBookingRequest request) {
        bookingRepository.softDeleteById(request.getBookingId());
        return ApiResponse.success(null, "Booking soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> cancelBooking(CancelBookingRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ApiException("Booking not found.", HttpStatus.NOT_FOUND));
        String oldStatus = booking.getBookingStatus() != null ? booking.getBookingStatus().getCode() : null;
        booking = bookingMapper.cancel(booking);
        bookingRepository.save(booking);
        bookingStatusHistoryService.logStatusChange(
                booking.getId(),
                oldStatus,
                booking.getBookingStatus().getCode(),
                "Booking cancelled",
                booking.getCustomer().getId()
        );
        return ApiResponse.success(null, "Booking cancelled successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> completeBooking(CompleteBookingRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ApiException("Booking not found.", HttpStatus.NOT_FOUND));
        String oldStatus = booking.getBookingStatus() != null ? booking.getBookingStatus().getCode() : null;
        booking = bookingMapper.complete(booking);
        bookingRepository.save(booking);
        bookingStatusHistoryService.logStatusChange(
                booking.getId(),
                oldStatus,
                booking.getBookingStatus().getCode(),
                "Booking completed",
                booking.getCustomer().getId()
        );
        return ApiResponse.success(null, "Booking completed successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> updateBookingStatus(UpdateBookingStatusRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ApiException("Booking not found.", HttpStatus.NOT_FOUND));
        String oldStatus = booking.getBookingStatus() != null ? booking.getBookingStatus().getCode() : null;
        booking = bookingMapper.updateStatus(booking, request.getStatusCode());
        bookingRepository.save(booking);
        bookingStatusHistoryService.logStatusChange(
                booking.getId(),
                oldStatus,
                request.getStatusCode(),
                "Booking status updated to " + request.getStatusCode(),
                booking.getCustomer().getId()
        );
        return ApiResponse.success(null, "Booking status updated successfully.");
    }

    @Override
    public boolean existsByBookingCode(String bookingCode) {
        return bookingRepository.existsByBookingCode(bookingCode);
    }
}
