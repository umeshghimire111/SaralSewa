package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByUserId(Integer userId);

    List<Notification> findByUserIdAndIsDeletedFalse(Integer userId);

    List<Notification> findByUserIdAndIsReadFalse(Integer userId);

    List<Notification> findByUserIdAndIsReadFalseAndIsDeletedFalse(Integer userId);

    List<Notification> findByNotificationType(String notificationType);

    List<Notification> findByIsActiveTrue();

    List<Notification> findByIsActiveTrueAndIsDeletedFalse();
}