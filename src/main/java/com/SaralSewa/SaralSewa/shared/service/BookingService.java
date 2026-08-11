package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.booking.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListBookingResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewBookingResponse;

public interface BookingService {

    ApiResponse<ViewBookingResponse> createBooking(CreateBookingRequest request);

    ApiResponse<ViewBookingResponse> updateBooking(UpdateBookingRequest request);

    ApiResponse<ViewBookingResponse> getBookingById(GetBookingByIdRequest request);

    ApiResponse<ViewBookingResponse> getBookingByCode(GetBookingByCodeRequest request);

    ApiResponse<PageResponse<ListBookingResponse>> getAllBookings(PageRequest pageRequest);

    ApiResponse<PageResponse<ListBookingResponse>> getBookingsByCustomer(GetBookingsByCustomerRequest request);

    ApiResponse<PageResponse<ListBookingResponse>> getBookingsByProvider(GetBookingsByProviderRequest request);

    ApiResponse<PageResponse<ListBookingResponse>> getBookingsByStatus(GetBookingsByStatusRequest request);

    ApiResponse<?> deleteBooking(DeleteBookingRequest request);

    ApiResponse<?> softDeleteBooking(SoftDeleteBookingRequest request);

    ApiResponse<?> cancelBooking(CancelBookingRequest request);

    ApiResponse<?> completeBooking(CompleteBookingRequest request);

    ApiResponse<?> updateBookingStatus(UpdateBookingStatusRequest request);

    boolean existsByBookingCode(String bookingCode);
}
