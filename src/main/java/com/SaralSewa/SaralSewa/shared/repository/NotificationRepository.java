package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByUserId(Integer userId);

    Page<Notification> findByUserId(Integer userId, Pageable pageable);

    List<Notification> findByUserIdAndIsDeletedFalse(Integer userId);

    List<Notification> findByUserIdAndIsReadFalse(Integer userId);

    List<Notification> findByUserIdAndIsReadFalseAndIsDeletedFalse(Integer userId);

    List<Notification> findByNotificationType(String notificationType);

    List<Notification> findByIsActiveTrue();

    List<Notification> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = true, n.updatedAt = CURRENT_TIMESTAMP WHERE n.id = :id")
    void markAsRead(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = true, n.updatedAt = CURRENT_TIMESTAMP WHERE n.user.id = :userId")
    void markAllAsReadByUser(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isDeleted = true, n.deletedAt = CURRENT_TIMESTAMP WHERE n.id = :id")
    void softDeleteById(@Param("id") Integer id);
}