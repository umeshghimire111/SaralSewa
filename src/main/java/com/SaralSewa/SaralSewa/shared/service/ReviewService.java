package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.review.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateReviewRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteReviewRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateReviewRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListReviewResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewReviewResponse;

public interface ReviewService {

    ApiResponse<ViewReviewResponse> createReview(CreateReviewRequest request);

    ApiResponse<ViewReviewResponse> updateReview(UpdateReviewRequest request);

    ApiResponse<ViewReviewResponse> getReviewById(GetReviewByIdRequest request);

    ApiResponse<PageResponse<ListReviewResponse>> getAllReviews(PageRequest pageRequest);

    ApiResponse<PageResponse<ListReviewResponse>> getReviewsByBooking(GetReviewsByBookingRequest request);

    ApiResponse<PageResponse<ListReviewResponse>> getReviewsByCustomer(GetReviewsByCustomerRequest request);

    ApiResponse<PageResponse<ListReviewResponse>> getReviewsByProvider(GetReviewsByProviderRequest request);

    ApiResponse<Double> getAverageRatingByProvider(GetAverageRatingByProviderRequest request);

    ApiResponse<Long> getReviewCountByProvider(GetReviewCountByProviderRequest request);

    ApiResponse<?> deleteReview(DeleteReviewRequest request);

    ApiResponse<?> softDeleteReview(SoftDeleteReviewRequest request);

    ApiResponse<?> activateReview(ActivateReviewRequest request);

    ApiResponse<?> deactivateReview(DeactivateReviewRequest request);
}
