package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateNotificationRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListNotificationResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewNotificationResponse;
import com.SaralSewa.SaralSewa.shared.entity.Notification;
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
public abstract class NotificationMapper {

    @Autowired
    private UserRepository userRepository;

    public ViewNotificationResponse viewDetails(Notification notification) {
        if (notification == null) return null;

        ViewNotificationResponse response = new ViewNotificationResponse();
        response.setUserId(Long.valueOf(notification.getUser() != null ? notification.getUser().getId() : null));
        response.setUserName(notification.getUser() != null ?
                notification.getUser().getFirstName() + (notification.getUser().getLastName() != null ? " " + notification.getUser().getLastName() : "") : null);
        response.setUserEmail(notification.getUser() != null ? notification.getUser().getEmail() : null);
        response.setTitle(notification.getTitle());
        response.setMessage(notification.getMessage());
        response.setNotificationType(notification.getNotificationType());
        response.setIsRead(notification.getIsRead());
        response.setIsActive(notification.getIsActive());
        response.setCreatedAt(notification.getCreatedAt());
        response.setUpdatedAt(notification.getUpdatedAt());

        return response;
    }

    public abstract ListNotificationResponse entityToResponse(Notification notification);

    public List<ListNotificationResponse> listNotifications(List<Notification> notifications) {
        if (notifications == null) return null;
        return notifications.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public Notification create(CreateNotificationRequest request) {
        if (request == null) return null;

        User user = userRepository.findByUserId(request.getUserId());

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setNotificationType(request.getNotificationType());
        notification.setIsRead(false);
        notification.setIsActive(true);
        notification.setIsDeleted(false);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());

        return notification;
    }

    public Notification update(UpdateNotificationRequest request, Notification notification) {
        if (request == null || notification == null) return notification;

        if (request.getTitle() != null) {
            notification.setTitle(request.getTitle());
        }
        if (request.getMessage() != null) {
            notification.setMessage(request.getMessage());
        }
        if (request.getNotificationType() != null) {
            notification.setNotificationType(request.getNotificationType());
        }
        if (request.getIsRead() != null) {
            notification.setIsRead(request.getIsRead());
        }
        notification.setUpdatedAt(LocalDateTime.now());
        return notification;
    }

    public Notification markAsRead(Notification notification) {
        if (notification == null) return notification;
        notification.setIsRead(true);
        notification.setUpdatedAt(LocalDateTime.now());
        return notification;
    }

    public Notification markAsUnread(Notification notification) {
        if (notification == null) return notification;
        notification.setIsRead(false);
        notification.setUpdatedAt(LocalDateTime.now());
        return notification;
    }

    public Notification deactivate(Notification notification) {
        if (notification == null) return notification;
        notification.setIsActive(false);
        notification.setUpdatedAt(LocalDateTime.now());
        return notification;
    }

    public Notification softDelete(Notification notification) {
        if (notification == null) return notification;
        notification.setIsDeleted(true);
        notification.setUpdatedAt(LocalDateTime.now());
        return notification;
    }
}