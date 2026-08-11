package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateReviewRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateReviewRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteReviewRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.review.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListReviewResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewReviewResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.REVIEWS)
@RequiredArgsConstructor
public class UserReviewController extends BaseController {

    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewReviewResponse> createReview(@Valid @RequestBody CreateReviewRequest request) {
        return reviewService.createReview(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewReviewResponse> updateReview(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateReviewRequest request) {
        request.setReviewId(id);
        return reviewService.updateReview(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewReviewResponse> getReviewById(@PathVariable Integer id) {
        GetReviewByIdRequest request = new GetReviewByIdRequest();
        request.setReviewId(id);
        return reviewService.getReviewById(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListReviewResponse>> getAllReviews(@Valid PageRequest pageRequest) {
        return reviewService.getAllReviews(pageRequest);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.BOOKING + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListReviewResponse>> getReviewsByBooking(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetReviewsByBookingRequest request = new GetReviewsByBookingRequest();
        request.setBookingId(id);
        request.setPageRequest(pageRequest);
        return reviewService.getReviewsByBooking(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CUSTOMER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListReviewResponse>> getReviewsByCustomer(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetReviewsByCustomerRequest request = new GetReviewsByCustomerRequest();
        request.setCustomerId(id);
        request.setPageRequest(pageRequest);
        return reviewService.getReviewsByCustomer(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.PROVIDER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListReviewResponse>> getReviewsByProvider(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetReviewsByProviderRequest request = new GetReviewsByProviderRequest();
        request.setProviderId(id);
        request.setPageRequest(pageRequest);
        return reviewService.getReviewsByProvider(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.AVERAGE_RATING + ApiConstant.SLASH + ApiConstant.PROVIDER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<Double> getAverageRatingByProvider(@PathVariable Integer id) {
        GetAverageRatingByProviderRequest request = new GetAverageRatingByProviderRequest();
        request.setProviderId(id);
        return reviewService.getAverageRatingByProvider(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.COUNT + ApiConstant.SLASH + ApiConstant.PROVIDER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<Long> getReviewCountByProvider(@PathVariable Integer id) {
        GetReviewCountByProviderRequest request = new GetReviewCountByProviderRequest();
        request.setProviderId(id);
        return reviewService.getReviewCountByProvider(request);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteReview(@PathVariable Integer id) {
        DeleteReviewRequest request = new DeleteReviewRequest();
        request.setReviewId(id);
        return reviewService.deleteReview(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteReview(@PathVariable Integer id) {
        SoftDeleteReviewRequest request = new SoftDeleteReviewRequest();
        request.setReviewId(id);
        return reviewService.softDeleteReview(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateReview(@PathVariable Integer id) {
        ActivateReviewRequest request = new ActivateReviewRequest();
        request.setReviewId(id);
        return reviewService.activateReview(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateReview(@PathVariable Integer id) {
        DeactivateReviewRequest request = new DeactivateReviewRequest();
        request.setReviewId(id);
        return reviewService.deactivateReview(request);
    }
}
