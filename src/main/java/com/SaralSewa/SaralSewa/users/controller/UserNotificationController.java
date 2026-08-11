package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.notification.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListNotificationResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewNotificationResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.NOTIFICATIONS)
@RequiredArgsConstructor
public class UserNotificationController extends BaseController {

    private final NotificationService notificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewNotificationResponse> createNotification(@Valid @RequestBody CreateNotificationRequest request) {
        return notificationService.createNotification(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewNotificationResponse> updateNotification(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateNotificationRequest request) {
        request.setNotificationId(id);
        return notificationService.updateNotification(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewNotificationResponse> getNotificationById(@PathVariable Integer id) {
        GetNotificationByIdRequest request = new GetNotificationByIdRequest();
        request.setNotificationId(id);
        return notificationService.getNotificationById(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListNotificationResponse>> getAllNotifications(@Valid PageRequest pageRequest) {
        return notificationService.getAllNotifications(pageRequest);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.USER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListNotificationResponse>> getNotificationsByUser(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetNotificationsByUserRequest request = new GetNotificationsByUserRequest();
        request.setUserId(id);
        request.setPageRequest(pageRequest);
        return notificationService.getNotificationsByUser(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.READ + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> markAsRead(@PathVariable Integer id) {
        MarkAsReadRequest request = new MarkAsReadRequest();
        request.setNotificationId(id);
        return notificationService.markAsRead(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.READ_ALL + ApiConstant.SLASH + ApiConstant.USER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> markAllAsReadByUser(@PathVariable Integer id) {
        MarkAllAsReadRequest request = new MarkAllAsReadRequest();
        request.setUserId(id);
        return notificationService.markAllAsReadByUser(request);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteNotification(@PathVariable Integer id) {
        DeleteNotificationRequest request = new DeleteNotificationRequest();
        request.setNotificationId(id);
        return notificationService.deleteNotification(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteNotification(@PathVariable Integer id) {
        SoftDeleteNotificationRequest request = new SoftDeleteNotificationRequest();
        request.setNotificationId(id);
        return notificationService.softDeleteNotification(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateNotification(@PathVariable Integer id) {
        DeactivateNotificationRequest request = new DeactivateNotificationRequest();
        request.setNotificationId(id);
        return notificationService.deactivateNotification(request);
    }
}
