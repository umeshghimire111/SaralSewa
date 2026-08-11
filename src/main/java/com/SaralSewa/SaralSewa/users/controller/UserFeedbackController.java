package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.feedback.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListFeedbackResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewFeedbackResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.FEEDBACKS)
@RequiredArgsConstructor
public class UserFeedbackController extends BaseController {

    private final FeedbackService feedbackService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewFeedbackResponse> createFeedback(@Valid @RequestBody CreateFeedbackRequest request) {
        return feedbackService.createFeedback(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewFeedbackResponse> updateFeedback(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateFeedbackRequest request) {
        request.setFeedbackId(id);
        return feedbackService.updateFeedback(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewFeedbackResponse> getFeedbackById(@PathVariable Integer id) {
        GetFeedbackByIdRequest request = new GetFeedbackByIdRequest();
        request.setFeedbackId(id);
        return feedbackService.getFeedbackById(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListFeedbackResponse>> getAllFeedbacks(@Valid PageRequest pageRequest) {
        return feedbackService.getAllFeedbacks(pageRequest);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.USER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListFeedbackResponse>> getFeedbacksByUser(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetFeedbacksByUserRequest request = new GetFeedbacksByUserRequest();
        request.setUserId(id);
        request.setPageRequest(pageRequest);
        return feedbackService.getFeedbacksByUser(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.TYPE + ApiConstant.SLASH + "{type}")
    public ApiResponse<PageResponse<ListFeedbackResponse>> getFeedbacksByType(
            @PathVariable String type,
            @Valid PageRequest pageRequest) {
        GetFeedbacksByTypeRequest request = new GetFeedbacksByTypeRequest();
        request.setFeedbackType(type);
        request.setPageRequest(pageRequest);
        return feedbackService.getFeedbacksByType(request);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteFeedback(@PathVariable Integer id) {
        DeleteFeedbackRequest request = new DeleteFeedbackRequest();
        request.setFeedbackId(id);
        return feedbackService.deleteFeedback(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteFeedback(@PathVariable Integer id) {
        SoftDeleteFeedbackRequest request = new SoftDeleteFeedbackRequest();
        request.setFeedbackId(id);
        return feedbackService.softDeleteFeedback(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateFeedback(@PathVariable Integer id) {
        ActivateFeedbackRequest request = new ActivateFeedbackRequest();
        request.setFeedbackId(id);
        return feedbackService.activateFeedback(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateFeedback(@PathVariable Integer id) {
        DeactivateFeedbackRequest request = new DeactivateFeedbackRequest();
        request.setFeedbackId(id);
        return feedbackService.deactivateFeedback(request);
    }
}
