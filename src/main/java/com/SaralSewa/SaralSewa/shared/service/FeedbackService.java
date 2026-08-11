package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.feedback.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListFeedbackResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewFeedbackResponse;

public interface FeedbackService {

    ApiResponse<ViewFeedbackResponse> createFeedback(CreateFeedbackRequest request);

    ApiResponse<ViewFeedbackResponse> updateFeedback(UpdateFeedbackRequest request);

    ApiResponse<ViewFeedbackResponse> getFeedbackById(GetFeedbackByIdRequest request);

    ApiResponse<PageResponse<ListFeedbackResponse>> getAllFeedbacks(PageRequest pageRequest);

    ApiResponse<PageResponse<ListFeedbackResponse>> getFeedbacksByUser(GetFeedbacksByUserRequest request);

    ApiResponse<PageResponse<ListFeedbackResponse>> getFeedbacksByType(GetFeedbacksByTypeRequest request);

    ApiResponse<?> deleteFeedback(DeleteFeedbackRequest request);

    ApiResponse<?> softDeleteFeedback(SoftDeleteFeedbackRequest request);

    ApiResponse<?> activateFeedback(ActivateFeedbackRequest request);

    ApiResponse<?> deactivateFeedback(DeactivateFeedbackRequest request);
}
