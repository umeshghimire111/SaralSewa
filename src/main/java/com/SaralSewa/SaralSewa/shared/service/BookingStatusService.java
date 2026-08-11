package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.bookingstatus.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListBookingStatusResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewBookingStatusResponse;

public interface BookingStatusService {

    ApiResponse<ViewBookingStatusResponse> createStatus(CreateBookingStatusRequest request);

    ApiResponse<ViewBookingStatusResponse> updateStatus(UpdateBookingStatusRequest request);

    ApiResponse<ViewBookingStatusResponse> getStatusById(GetBookingStatusByIdRequest request);

    ApiResponse<ViewBookingStatusResponse> getStatusByCode(GetBookingStatusByCodeRequest request);

    ApiResponse<PageResponse<ListBookingStatusResponse>> getAllStatuses(PageRequest pageRequest);

    ApiResponse<?> deleteStatus(DeleteBookingStatusRequest request);

    ApiResponse<?> softDeleteStatus(SoftDeleteBookingStatusRequest request);

    ApiResponse<?> activateStatus(ActivateBookingStatusRequest request);

    ApiResponse<?> deactivateStatus(DeactivateBookingStatusRequest request);

    boolean existsByCode(String code);
}
