package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.review.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateReviewRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateReviewRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteReviewRequest;

import com.SaralSewa.SaralSewa.shared.dto.response.list.ListReviewResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewReviewResponse;
import com.SaralSewa.SaralSewa.shared.entity.Review;
import com.SaralSewa.SaralSewa.shared.entity.ServiceProvider;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.ReviewMapper;
import com.SaralSewa.SaralSewa.shared.repository.ReviewRepository;
import com.SaralSewa.SaralSewa.shared.repository.ServiceProviderRepository;
import com.SaralSewa.SaralSewa.shared.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ServiceProviderRepository serviceProviderRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ApiResponse<ViewReviewResponse> createReview(CreateReviewRequest request) {
        Review review = reviewMapper.create(request);
        Review savedReview = reviewRepository.save(review);

        // Update provider rating
        ServiceProvider provider = serviceProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new ApiException("Provider not found.", HttpStatus.NOT_FOUND));
        Double avgRating = reviewRepository.getAverageRatingByProvider(request.getProviderId());
        if (avgRating != null) {
            provider.setAverageRating(BigDecimal.valueOf(avgRating));
        }
        long count = reviewRepository.countReviewsByProvider(request.getProviderId());
        provider.setTotalReviews((int) count);
        serviceProviderRepository.save(provider);

        return ApiResponse.created(reviewMapper.viewDetails(savedReview), "Review created successfully.");
    }

    @Override
    public ApiResponse<ViewReviewResponse> updateReview(UpdateReviewRequest request) {
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new ApiException("Review not found.", HttpStatus.NOT_FOUND));
        review = reviewMapper.update(request, review);
        Review updatedReview = reviewRepository.save(review);
        return ApiResponse.success(reviewMapper.viewDetails(updatedReview), "Review updated successfully.");
    }

    @Override
    public ApiResponse<ViewReviewResponse> getReviewById(GetReviewByIdRequest request) {
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new ApiException("Review not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(reviewMapper.viewDetails(review), "Review retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListReviewResponse>> getAllReviews(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Review> reviews = reviewRepository.findAll(pageable);
        Page<ListReviewResponse> response = reviews.map(reviewMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Reviews retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListReviewResponse>> getReviewsByBooking(GetReviewsByBookingRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<Review> reviews = reviewRepository.findByBookingId(request.getBookingId(), pageable);
        Page<ListReviewResponse> response = reviews.map(reviewMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Reviews by booking retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListReviewResponse>> getReviewsByCustomer(GetReviewsByCustomerRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<Review> reviews = reviewRepository.findByCustomerId(request.getCustomerId(), pageable);
        Page<ListReviewResponse> response = reviews.map(reviewMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Reviews by customer retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListReviewResponse>> getReviewsByProvider(GetReviewsByProviderRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<Review> reviews = reviewRepository.findByProviderId(request.getProviderId(), pageable);
        Page<ListReviewResponse> response = reviews.map(reviewMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Reviews by provider retrieved successfully.");
    }

    @Override
    public ApiResponse<Double> getAverageRatingByProvider(GetAverageRatingByProviderRequest request) {
        Double avgRating = reviewRepository.getAverageRatingByProvider(request.getProviderId());
        if (avgRating == null) {
            avgRating = 0.0;
        }
        return ApiResponse.success(avgRating, "Average rating retrieved successfully.");
    }

    @Override
    public ApiResponse<Long> getReviewCountByProvider(GetReviewCountByProviderRequest request) {
        long count = reviewRepository.countReviewsByProvider(request.getProviderId());
        return ApiResponse.success(count, "Review count retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteReview(DeleteReviewRequest request) {
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new ApiException("Review not found.", HttpStatus.NOT_FOUND));
        reviewRepository.delete(review);
        return ApiResponse.success(null, "Review deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteReview(SoftDeleteReviewRequest request) {
        reviewRepository.softDeleteById(request.getReviewId());
        return ApiResponse.success(null, "Review soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateReview(ActivateReviewRequest request) {
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new ApiException("Review not found.", HttpStatus.NOT_FOUND));
        review = reviewMapper.activate(review);
        reviewRepository.save(review);
        return ApiResponse.success(null, "Review activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateReview(DeactivateReviewRequest request) {
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new ApiException("Review not found.", HttpStatus.NOT_FOUND));
        review = reviewMapper.deactivate(review);
        reviewRepository.save(review);
        return ApiResponse.success(null, "Review deactivated successfully.");
    }
}
