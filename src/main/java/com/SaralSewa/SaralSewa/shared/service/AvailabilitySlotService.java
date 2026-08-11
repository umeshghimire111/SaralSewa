package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.slot.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListAvailabilitySlotResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewAvailabilitySlotResponse;

public interface AvailabilitySlotService {

    ApiResponse<ViewAvailabilitySlotResponse> createSlot(CreateAvailabilitySlotRequest request);

    ApiResponse<ViewAvailabilitySlotResponse> updateSlot(UpdateAvailabilitySlotRequest request);

    ApiResponse<ViewAvailabilitySlotResponse> getSlotById(GetSlotByIdRequest request);

    ApiResponse<PageResponse<ListAvailabilitySlotResponse>> getAllSlots(PageRequest pageRequest);

    ApiResponse<PageResponse<ListAvailabilitySlotResponse>> getSlotsByProvider(GetSlotsByProviderRequest request);

    ApiResponse<?> deleteSlot(DeleteAvailabilitySlotRequest request);

    ApiResponse<?> softDeleteSlot(SoftDeleteSlotRequest request);

    ApiResponse<?> markAsBooked(MarkAsBookedRequest request);

    ApiResponse<?> markAsAvailable(MarkAsAvailableRequest request);

    ApiResponse<?> activateSlot(ActivateSlotRequest request);

    ApiResponse<?> deactivateSlot(DeactivateSlotRequest request);
}
