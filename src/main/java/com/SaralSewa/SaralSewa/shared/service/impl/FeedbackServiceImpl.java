package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.feedback.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListFeedbackResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewFeedbackResponse;
import com.SaralSewa.SaralSewa.shared.entity.Feedback;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.FeedbackMapper;
import com.SaralSewa.SaralSewa.shared.repository.FeedbackRepository;
import com.SaralSewa.SaralSewa.shared.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public ApiResponse<ViewFeedbackResponse> createFeedback(CreateFeedbackRequest request) {
        Feedback feedback = feedbackMapper.create(request);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return ApiResponse.created(feedbackMapper.viewDetails(savedFeedback), "Feedback created successfully.");
    }

    @Override
    public ApiResponse<ViewFeedbackResponse> updateFeedback(UpdateFeedbackRequest request) {
        Feedback feedback = feedbackRepository.findById(request.getFeedbackId())
                .orElseThrow(() -> new ApiException("Feedback not found.", HttpStatus.NOT_FOUND));
        feedback = feedbackMapper.update(request, feedback);
        Feedback updatedFeedback = feedbackRepository.save(feedback);
        return ApiResponse.success(feedbackMapper.viewDetails(updatedFeedback), "Feedback updated successfully.");
    }

    @Override
    public ApiResponse<ViewFeedbackResponse> getFeedbackById(GetFeedbackByIdRequest request) {
        Feedback feedback = feedbackRepository.findById(request.getFeedbackId())
                .orElseThrow(() -> new ApiException("Feedback not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(feedbackMapper.viewDetails(feedback), "Feedback retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListFeedbackResponse>> getAllFeedbacks(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Feedback> feedbacks = feedbackRepository.findAll(pageable);
        Page<ListFeedbackResponse> response = feedbacks.map(feedbackMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Feedbacks retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListFeedbackResponse>> getFeedbacksByUser(GetFeedbacksByUserRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<Feedback> feedbacks = feedbackRepository.findByUserId(request.getUserId(), pageable);
        Page<ListFeedbackResponse> response = feedbacks.map(feedbackMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "User feedbacks retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListFeedbackResponse>> getFeedbacksByType(GetFeedbacksByTypeRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<Feedback> feedbacks = feedbackRepository.findByFeedbackType(request.getFeedbackType(), pageable);
        Page<ListFeedbackResponse> response = feedbacks.map(feedbackMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Feedbacks by type retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteFeedback(DeleteFeedbackRequest request) {
        Feedback feedback = feedbackRepository.findById(request.getFeedbackId())
                .orElseThrow(() -> new ApiException("Feedback not found.", HttpStatus.NOT_FOUND));
        feedbackRepository.delete(feedback);
        return ApiResponse.success(null, "Feedback deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteFeedback(SoftDeleteFeedbackRequest request) {
        feedbackRepository.softDeleteById(request.getFeedbackId());
        return ApiResponse.success(null, "Feedback soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateFeedback(ActivateFeedbackRequest request) {
        Feedback feedback = feedbackRepository.findById(request.getFeedbackId())
                .orElseThrow(() -> new ApiException("Feedback not found.", HttpStatus.NOT_FOUND));
        feedback = feedbackMapper.activate(feedback);
        feedbackRepository.save(feedback);
        return ApiResponse.success(null, "Feedback activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateFeedback(DeactivateFeedbackRequest request) {
        Feedback feedback = feedbackRepository.findById(request.getFeedbackId())
                .orElseThrow(() -> new ApiException("Feedback not found.", HttpStatus.NOT_FOUND));
        feedback = feedbackMapper.deactivate(feedback);
        feedbackRepository.save(feedback);
        return ApiResponse.success(null, "Feedback deactivated successfully.");
    }
}
