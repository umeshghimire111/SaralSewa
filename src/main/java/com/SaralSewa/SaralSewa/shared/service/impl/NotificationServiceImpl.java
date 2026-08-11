package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.notification.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListNotificationResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewNotificationResponse;
import com.SaralSewa.SaralSewa.shared.entity.Notification;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.NotificationMapper;
import com.SaralSewa.SaralSewa.shared.repository.NotificationRepository;
import com.SaralSewa.SaralSewa.shared.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public ApiResponse<ViewNotificationResponse> createNotification(CreateNotificationRequest request) {
        Notification notification = notificationMapper.create(request);
        Notification savedNotification = notificationRepository.save(notification);
        return ApiResponse.created(notificationMapper.viewDetails(savedNotification), "Notification created successfully.");
    }

    @Override
    public ApiResponse<ViewNotificationResponse> updateNotification(UpdateNotificationRequest request) {
        Notification notification = notificationRepository.findById(request.getNotificationId())
                .orElseThrow(() -> new ApiException("Notification not found.", HttpStatus.NOT_FOUND));
        notification = notificationMapper.update(request, notification);
        Notification updatedNotification = notificationRepository.save(notification);
        return ApiResponse.success(notificationMapper.viewDetails(updatedNotification), "Notification updated successfully.");
    }

    @Override
    public ApiResponse<ViewNotificationResponse> getNotificationById(GetNotificationByIdRequest request) {
        Notification notification = notificationRepository.findById(request.getNotificationId())
                .orElseThrow(() -> new ApiException("Notification not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(notificationMapper.viewDetails(notification), "Notification retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListNotificationResponse>> getAllNotifications(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Notification> notifications = notificationRepository.findAll(pageable);
        Page<ListNotificationResponse> response = notifications.map(notificationMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Notifications retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListNotificationResponse>> getNotificationsByUser(GetNotificationsByUserRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<Notification> notifications = notificationRepository.findByUserId(request.getUserId(), pageable);
        Page<ListNotificationResponse> response = notifications.map(notificationMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "User notifications retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> markAsRead(MarkAsReadRequest request) {
        notificationRepository.markAsRead(request.getNotificationId());
        return ApiResponse.success(null, "Notification marked as read.");
    }

    @Override
    @Transactional
    public ApiResponse<?> markAllAsReadByUser(MarkAllAsReadRequest request) {
        notificationRepository.markAllAsReadByUser(request.getUserId());
        return ApiResponse.success(null, "All notifications marked as read.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteNotification(DeleteNotificationRequest request) {
        Notification notification = notificationRepository.findById(request.getNotificationId())
                .orElseThrow(() -> new ApiException("Notification not found.", HttpStatus.NOT_FOUND));
        notificationRepository.delete(notification);
        return ApiResponse.success(null, "Notification deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteNotification(SoftDeleteNotificationRequest request) {
        notificationRepository.softDeleteById(request.getNotificationId());
        return ApiResponse.success(null, "Notification soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateNotification(DeactivateNotificationRequest request) {
        Notification notification = notificationRepository.findById(request.getNotificationId())
                .orElseThrow(() -> new ApiException("Notification not found.", HttpStatus.NOT_FOUND));
        notification.setIsActive(false);
        notification.setUpdatedAt(java.time.LocalDateTime.now());
        notificationRepository.save(notification);
        return ApiResponse.success(null, "Notification deactivated successfully.");
    }
}
