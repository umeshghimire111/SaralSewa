package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.notification.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListNotificationResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewNotificationResponse;

public interface NotificationService {

    ApiResponse<ViewNotificationResponse> createNotification(CreateNotificationRequest request);

    ApiResponse<ViewNotificationResponse> updateNotification(UpdateNotificationRequest request);

    ApiResponse<ViewNotificationResponse> getNotificationById(GetNotificationByIdRequest request);

    ApiResponse<PageResponse<ListNotificationResponse>> getAllNotifications(PageRequest pageRequest);

    ApiResponse<PageResponse<ListNotificationResponse>> getNotificationsByUser(GetNotificationsByUserRequest request);

    ApiResponse<?> markAsRead(MarkAsReadRequest request);

    ApiResponse<?> markAllAsReadByUser(MarkAllAsReadRequest request);

    ApiResponse<?> deleteNotification(DeleteNotificationRequest request);

    ApiResponse<?> softDeleteNotification(SoftDeleteNotificationRequest request);

    ApiResponse<?> deactivateNotification(DeactivateNotificationRequest request);
}
