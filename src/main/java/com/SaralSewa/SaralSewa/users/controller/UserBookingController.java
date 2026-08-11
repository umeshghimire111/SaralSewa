package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.booking.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListBookingResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewBookingResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.BOOKINGS)
@RequiredArgsConstructor
public class UserBookingController extends BaseController {

    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewBookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        return bookingService.createBooking(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewBookingResponse> updateBooking(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateBookingRequest request) {
        request.setBookingId(id);
        return bookingService.updateBooking(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewBookingResponse> getBookingById(@PathVariable Integer id) {
        GetBookingByIdRequest request = new GetBookingByIdRequest();
        request.setBookingId(id);
        return bookingService.getBookingById(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CODE + ApiConstant.SLASH + "{code}")
    public ApiResponse<ViewBookingResponse> getBookingByCode(@PathVariable String code) {
        GetBookingByCodeRequest request = new GetBookingByCodeRequest();
        request.setBookingCode(code);
        return bookingService.getBookingByCode(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListBookingResponse>> getAllBookings(@Valid PageRequest pageRequest) {
        return bookingService.getAllBookings(pageRequest);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CUSTOMER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListBookingResponse>> getBookingsByCustomer(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetBookingsByCustomerRequest request = new GetBookingsByCustomerRequest();
        request.setCustomerId(id);
        request.setPageRequest(pageRequest);
        return bookingService.getBookingsByCustomer(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.PROVIDER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListBookingResponse>> getBookingsByProvider(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetBookingsByProviderRequest request = new GetBookingsByProviderRequest();
        request.setProviderId(id);
        request.setPageRequest(pageRequest);
        return bookingService.getBookingsByProvider(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.STATUS + ApiConstant.SLASH + "{status}")
    public ApiResponse<PageResponse<ListBookingResponse>> getBookingsByStatus(
            @PathVariable String status,
            @Valid PageRequest pageRequest) {
        GetBookingsByStatusRequest request = new GetBookingsByStatusRequest();
        request.setStatusCode(status);
        request.setPageRequest(pageRequest);
        return bookingService.getBookingsByStatus(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.CANCEL + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> cancelBooking(@PathVariable Integer id) {
        CancelBookingRequest request = new CancelBookingRequest();
        request.setBookingId(id);
        return bookingService.cancelBooking(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.COMPLETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> completeBooking(@PathVariable Integer id) {
        CompleteBookingRequest request = new CompleteBookingRequest();
        request.setBookingId(id);
        return bookingService.completeBooking(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.STATUS + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> updateBookingStatus(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateBookingStatusRequest request) {
        request.setBookingId(id);
        return bookingService.updateBookingStatus(request);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteBooking(@PathVariable Integer id) {
        DeleteBookingRequest request = new DeleteBookingRequest();
        request.setBookingId(id);
        return bookingService.deleteBooking(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteBooking(@PathVariable Integer id) {
        SoftDeleteBookingRequest request = new SoftDeleteBookingRequest();
        request.setBookingId(id);
        return bookingService.softDeleteBooking(request);
    }
}
