package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteAvailabilitySlotRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.slot.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListAvailabilitySlotResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewAvailabilitySlotResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.AvailabilitySlotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.SLOTS)
@RequiredArgsConstructor
public class UserAvailabilitySlotController extends BaseController {

    private final AvailabilitySlotService availabilitySlotService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewAvailabilitySlotResponse> createSlot(@Valid @RequestBody CreateAvailabilitySlotRequest request) {
        return availabilitySlotService.createSlot(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewAvailabilitySlotResponse> updateSlot(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateAvailabilitySlotRequest request) {
        request.setSlotId(id);
        return availabilitySlotService.updateSlot(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewAvailabilitySlotResponse> getSlotById(@PathVariable Integer id) {
        GetSlotByIdRequest request = new GetSlotByIdRequest();
        request.setSlotId(id);
        return availabilitySlotService.getSlotById(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListAvailabilitySlotResponse>> getAllSlots(@Valid PageRequest pageRequest) {
        return availabilitySlotService.getAllSlots(pageRequest);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.PROVIDER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListAvailabilitySlotResponse>> getSlotsByProvider(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetSlotsByProviderRequest request = new GetSlotsByProviderRequest();
        request.setProviderId(id);
        request.setPageRequest(pageRequest);
        return availabilitySlotService.getSlotsByProvider(request);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteSlot(@PathVariable Integer id) {
        DeleteAvailabilitySlotRequest request = new DeleteAvailabilitySlotRequest();
        request.setSlotId(id);
        return availabilitySlotService.deleteSlot(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteSlot(@PathVariable Integer id) {
        SoftDeleteSlotRequest request = new SoftDeleteSlotRequest();
        request.setSlotId(id);
        return availabilitySlotService.softDeleteSlot(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.BOOK + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> markAsBooked(@PathVariable Integer id) {
        MarkAsBookedRequest request = new MarkAsBookedRequest();
        request.setSlotId(id);
        return availabilitySlotService.markAsBooked(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.AVAILABLE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> markAsAvailable(@PathVariable Integer id) {
        MarkAsAvailableRequest request = new MarkAsAvailableRequest();
        request.setSlotId(id);
        return availabilitySlotService.markAsAvailable(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateSlot(@PathVariable Integer id) {
        ActivateSlotRequest request = new ActivateSlotRequest();
        request.setSlotId(id);
        return availabilitySlotService.activateSlot(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateSlot(@PathVariable Integer id) {
        DeactivateSlotRequest request = new DeactivateSlotRequest();
        request.setSlotId(id);
        return availabilitySlotService.deactivateSlot(request);
    }
}
