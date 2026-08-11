package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.history.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListBookingStatusHistoryResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewBookingStatusHistoryResponse;

public interface BookingStatusHistoryService {

    ApiResponse<ViewBookingStatusHistoryResponse> getHistoryById(GetHistoryByIdRequest request);

    ApiResponse<PageResponse<ListBookingStatusHistoryResponse>> getAllHistories(PageRequest pageRequest);

    ApiResponse<PageResponse<ListBookingStatusHistoryResponse>> getHistoriesByBooking(GetHistoriesByBookingRequest request);

    ApiResponse<?> logStatusChange(Integer bookingId, String oldStatusCode, String newStatusCode, String remarks, Integer changedBy);
}
