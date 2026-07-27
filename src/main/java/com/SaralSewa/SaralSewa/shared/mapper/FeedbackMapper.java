package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateFeedbackRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListFeedbackResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewFeedbackResponse;
import com.SaralSewa.SaralSewa.shared.entity.Feedback;
import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class FeedbackMapper {

    @Autowired
    private UserRepository userRepository;

    public ViewFeedbackResponse viewDetails(Feedback feedback) {
        if (feedback == null) return null;

        ViewFeedbackResponse response = new ViewFeedbackResponse();
        response.setUserId((feedback.getUser() != null ? feedback.getUser().getId() : null));
        response.setUserName(feedback.getUser() != null ?
                feedback.getUser().getFirstName() + (feedback.getUser().getLastName() != null ? " " + feedback.getUser().getLastName() : "") : null);
        response.setUserEmail(feedback.getUser() != null ? feedback.getUser().getEmail() : null);
        response.setSubject(feedback.getSubject());
        response.setMessage(feedback.getMessage());
        response.setFeedbackType(feedback.getFeedbackType());
        response.setIsActive(feedback.getIsActive());
        response.setCreatedAt(feedback.getCreatedAt());
        response.setUpdatedAt(feedback.getUpdatedAt());

        return response;
    }

    public abstract ListFeedbackResponse entityToResponse(Feedback feedback);

    public List<ListFeedbackResponse> listFeedbacks(List<Feedback> feedbacks) {
        if (feedbacks == null) return null;
        return feedbacks.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public Feedback create(CreateFeedbackRequest request) {
        if (request == null) return null;

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUserId()));
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setSubject(request.getSubject());
        feedback.setMessage(request.getMessage());
        feedback.setFeedbackType(request.getFeedbackType());
        feedback.setIsActive(true);
        feedback.setIsDeleted(false);
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setUpdatedAt(LocalDateTime.now());

        return feedback;
    }

    public Feedback update(UpdateFeedbackRequest request, Feedback feedback) {
        if (request == null || feedback == null) return feedback;

        if (request.getSubject() != null) {
            feedback.setSubject(request.getSubject());
        }
        if (request.getMessage() != null) {
            feedback.setMessage(request.getMessage());
        }
        if (request.getFeedbackType() != null) {
            feedback.setFeedbackType(request.getFeedbackType());
        }
        feedback.setUpdatedAt(LocalDateTime.now());
        return feedback;
    }

    public Feedback deactivate(Feedback feedback) {
        if (feedback == null) return feedback;
        feedback.setIsActive(false);
        feedback.setUpdatedAt(LocalDateTime.now());
        return feedback;
    }

    public Feedback activate(Feedback feedback) {
        if (feedback == null) return feedback;
        feedback.setIsActive(true);
        feedback.setUpdatedAt(LocalDateTime.now());
        return feedback;
    }

    public Feedback softDelete(Feedback feedback) {
        if (feedback == null) return feedback;
        feedback.setIsDeleted(true);
        feedback.setUpdatedAt(LocalDateTime.now());
        return feedback;
    }
}