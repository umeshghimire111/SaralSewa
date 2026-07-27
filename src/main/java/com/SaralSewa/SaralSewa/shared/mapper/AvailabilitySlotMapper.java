package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.response.view.*;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListAvailabilitySlotResponse;
import com.SaralSewa.SaralSewa.shared.entity.AvailabilitySlot;
import com.SaralSewa.SaralSewa.shared.entity.ServiceProvider;
import com.SaralSewa.SaralSewa.shared.repository.ServiceProviderRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AvailabilitySlotMapper {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public ViewAvailabilitySlotResponse viewDetails(AvailabilitySlot slot) {
        if (slot == null) return null;

        ViewAvailabilitySlotResponse response = new ViewAvailabilitySlotResponse();
        response.setProviderId((slot.getProvider() != null ? slot.getProvider().getId() : null));
        response.setProviderName(slot.getProvider() != null ? slot.getProvider().getProfession() : null);
        response.setProviderProfession(slot.getProvider() != null ? slot.getProvider().getProfession() : null);
        response.setAvailableDate(slot.getAvailableDate());
        response.setStartTime(slot.getStartTime());
        response.setEndTime(slot.getEndTime());
        response.setIsBooked(slot.getIsBooked());
        response.setIsActive(slot.getIsActive());
        response.setCreatedAt(slot.getCreatedAt());
        response.setUpdatedAt(slot.getUpdatedAt());

        return response;
    }

    public abstract ListAvailabilitySlotResponse entityToResponse(AvailabilitySlot slot);

    public List<ListAvailabilitySlotResponse> listSlots(List<AvailabilitySlot> slots) {
        if (slots == null) return null;
        return slots.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public AvailabilitySlot create(CreateAvailabilitySlotRequest request) {
        if (request == null) return null;

        ServiceProvider provider = serviceProviderRepository.findByUserId(request.getProviderId()).orElse(null);

        AvailabilitySlot slot = new AvailabilitySlot();
        slot.setProvider(provider);
        slot.setAvailableDate(request.getAvailableDate());
        slot.setStartTime(request.getStartTime());
        slot.setEndTime(request.getEndTime());
        slot.setIsBooked(false);
        slot.setIsActive(true);
        slot.setIsDeleted(false);
        slot.setCreatedAt(LocalDateTime.now());
        slot.setUpdatedAt(LocalDateTime.now());

        return slot;
    }

    public AvailabilitySlot update(UpdateAvailabilitySlotRequest request, AvailabilitySlot slot) {
        if (request == null || slot == null) return slot;

        if (request.getAvailableDate() != null) {
            slot.setAvailableDate(request.getAvailableDate());
        }
        if (request.getStartTime() != null) {
            slot.setStartTime(request.getStartTime());
        }
        if (request.getEndTime() != null) {
            slot.setEndTime(request.getEndTime());
        }
        if (request.getIsBooked() != null) {
            slot.setIsBooked(request.getIsBooked());
        }
        slot.setUpdatedAt(LocalDateTime.now());
        return slot;
    }

    public AvailabilitySlot markAsBooked(AvailabilitySlot slot) {
        if (slot == null) return slot;
        slot.setIsBooked(true);
        slot.setUpdatedAt(LocalDateTime.now());
        return slot;
    }

    public AvailabilitySlot markAsAvailable(AvailabilitySlot slot) {
        if (slot == null) return slot;
        slot.setIsBooked(false);
        slot.setUpdatedAt(LocalDateTime.now());
        return slot;
    }

    public AvailabilitySlot deactivate(AvailabilitySlot slot) {
        if (slot == null) return slot;
        slot.setIsActive(false);
        slot.setUpdatedAt(LocalDateTime.now());
        return slot;
    }

    public AvailabilitySlot activate(AvailabilitySlot slot) {
        if (slot == null) return slot;
        slot.setIsActive(true);
        slot.setUpdatedAt(LocalDateTime.now());
        return slot;
    }

    public AvailabilitySlot softDelete(AvailabilitySlot slot) {
        if (slot == null) return slot;
        slot.setIsDeleted(true);
        slot.setUpdatedAt(LocalDateTime.now());
        return slot;
    }
}