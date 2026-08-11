package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ResponseUtil;
import com.SaralSewa.SaralSewa.shared.dto.request.action.slot.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListAvailabilitySlotResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewAvailabilitySlotResponse;
import com.SaralSewa.SaralSewa.shared.entity.AvailabilitySlot;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.AvailabilitySlotMapper;
import com.SaralSewa.SaralSewa.shared.repository.AvailabilitySlotRepository;
import com.SaralSewa.SaralSewa.shared.service.AvailabilitySlotService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AvailabilitySlotServiceImpl implements AvailabilitySlotService {

    private final AvailabilitySlotRepository availabilitySlotRepository;
    private final AvailabilitySlotMapper availabilitySlotMapper;

    @Override
    public ApiResponse<ViewAvailabilitySlotResponse> createSlot(CreateAvailabilitySlotRequest request) {
        AvailabilitySlot slot = availabilitySlotMapper.create(request);
        AvailabilitySlot savedSlot = availabilitySlotRepository.save(slot);
        return  ResponseUtil.getSuccessfulApiResponse(availabilitySlotMapper.viewDetails(savedSlot), "Slot created successfully.");
    }

    @Override
    public ApiResponse<ViewAvailabilitySlotResponse> updateSlot(UpdateAvailabilitySlotRequest request) {
        AvailabilitySlot slot = availabilitySlotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new ApiException("Slot not found.", HttpStatus.NOT_FOUND));
        slot = availabilitySlotMapper.update(request, slot);
        AvailabilitySlot updatedSlot = availabilitySlotRepository.save(slot);
        return  ResponseUtil.getSuccessfulApiResponse(availabilitySlotMapper.viewDetails(updatedSlot), "Slot updated successfully.");
    }

    @Override
    public ApiResponse<ViewAvailabilitySlotResponse> getSlotById(GetSlotByIdRequest request) {
        AvailabilitySlot slot = availabilitySlotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new ApiException("Slot not found.", HttpStatus.NOT_FOUND));
        return  ResponseUtil.getSuccessfulApiResponse(availabilitySlotMapper.viewDetails(slot), "Slot retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListAvailabilitySlotResponse>> getAllSlots(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<AvailabilitySlot> slots = availabilitySlotRepository.findAll(pageable);
        Page<ListAvailabilitySlotResponse> response = slots.map(availabilitySlotMapper::entityToResponse);
        return  ResponseUtil.getSuccessfulApiResponse(PageResponse.from(response), "Slots retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListAvailabilitySlotResponse>> getSlotsByProvider(GetSlotsByProviderRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<AvailabilitySlot> slots = availabilitySlotRepository.findByProviderId(request.getProviderId(), pageable);
        Page<ListAvailabilitySlotResponse> response = slots.map(availabilitySlotMapper::entityToResponse);
        return  ResponseUtil.getSuccessfulApiResponse(PageResponse.from(response), "Provider slots retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteSlot(DeleteAvailabilitySlotRequest request) {
        AvailabilitySlot slot = availabilitySlotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new ApiException("Slot not found.", HttpStatus.NOT_FOUND));
        availabilitySlotRepository.delete(slot);
        return  ResponseUtil.getSuccessfulApiResponse(null, "Slot deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteSlot(SoftDeleteSlotRequest request) {
        availabilitySlotRepository.softDeleteById(request.getSlotId());
        return  ResponseUtil.getSuccessfulApiResponse(null, "Slot soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> markAsBooked(MarkAsBookedRequest request) {
        availabilitySlotRepository.markAsBooked(request.getSlotId());
        return  ResponseUtil.getSuccessfulApiResponse(null, "Slot marked as booked.");
    }

    @Override
    @Transactional
    public ApiResponse<?> markAsAvailable(MarkAsAvailableRequest request) {
        availabilitySlotRepository.markAsAvailable(request.getSlotId());
        return  ResponseUtil.getSuccessfulApiResponse(null, "Slot marked as available.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateSlot(ActivateSlotRequest request) {
        AvailabilitySlot slot = availabilitySlotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new ApiException("Slot not found.", HttpStatus.NOT_FOUND));
        slot = availabilitySlotMapper.activate(slot);
        availabilitySlotRepository.save(slot);
        return  ResponseUtil.getSuccessfulApiResponse(null, "Slot activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateSlot(DeactivateSlotRequest request) {
        AvailabilitySlot slot = availabilitySlotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new ApiException("Slot not found.", HttpStatus.NOT_FOUND));
        slot = availabilitySlotMapper.deactivate(slot);
        availabilitySlotRepository.save(slot);
        return ResponseUtil.getSuccessfulApiResponse(null, "Slot deactivated successfully.");
    }
}
