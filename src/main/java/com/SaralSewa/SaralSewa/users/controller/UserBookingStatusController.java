package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteBookingStatusRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.bookingstatus.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListBookingStatusResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewBookingStatusResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.BookingStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.BOOKING_STATUSES)
@RequiredArgsConstructor
public class UserBookingStatusController extends BaseController {

    private final BookingStatusService bookingStatusService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewBookingStatusResponse> createStatus(@Valid @RequestBody CreateBookingStatusRequest request) {
        return bookingStatusService.createStatus(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewBookingStatusResponse> updateStatus(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateBookingStatusRequest request) {
        request.setBookingStatusId(id);
        return bookingStatusService.updateStatus(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewBookingStatusResponse> getStatusById(@PathVariable Integer id) {
        GetBookingStatusByIdRequest request = new GetBookingStatusByIdRequest();
        request.setBookingStatusId(id);
        return bookingStatusService.getStatusById(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CODE + ApiConstant.SLASH + "{code}")
    public ApiResponse<ViewBookingStatusResponse> getStatusByCode(@PathVariable String code) {
        GetBookingStatusByCodeRequest request = new GetBookingStatusByCodeRequest();
        request.setCode(code);
        return bookingStatusService.getStatusByCode(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListBookingStatusResponse>> getAllStatuses(@Valid PageRequest pageRequest) {
        return bookingStatusService.getAllStatuses(pageRequest);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteStatus(@PathVariable Integer id) {
        DeleteBookingStatusRequest request = new DeleteBookingStatusRequest();
        request.setBookingStatusId(id);
        return bookingStatusService.deleteStatus(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteStatus(@PathVariable Integer id) {
        SoftDeleteBookingStatusRequest request = new SoftDeleteBookingStatusRequest();
        request.setBookingStatusId(id);
        return bookingStatusService.softDeleteStatus(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateStatus(@PathVariable Integer id) {
        ActivateBookingStatusRequest request = new ActivateBookingStatusRequest();
        request.setBookingStatusId(id);
        return bookingStatusService.activateStatus(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateStatus(@PathVariable Integer id) {
        DeactivateBookingStatusRequest request = new DeactivateBookingStatusRequest();
        request.setBookingStatusId(id);
        return bookingStatusService.deactivateStatus(request);
    }
}
