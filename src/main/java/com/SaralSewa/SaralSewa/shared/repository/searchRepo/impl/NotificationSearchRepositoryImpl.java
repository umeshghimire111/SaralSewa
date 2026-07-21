package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.Notification;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.NotificationSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class NotificationSearchRepositoryImpl implements NotificationSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(n) FROM Notification n
            WHERE (:id IS NULL OR n.id = :id)
            AND (:userId IS NULL OR n.user.id = :userId)
            AND (:title IS NULL OR LOWER(n.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND (:notificationType IS NULL OR n.notificationType = :notificationType)
            AND (:isRead IS NULL OR n.isRead = :isRead)
            AND (:isActive IS NULL OR n.isActive = :isActive)
            AND (:isDeleted IS NULL OR n.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("userId", SearchParamUtil.getInteger(searchParam, "USER_ID"))
                .setParameter("title", SearchParamUtil.getString(searchParam, "TITLE"))
                .setParameter("notificationType", SearchParamUtil.getString(searchParam, "NOTIFICATION_TYPE"))
                .setParameter("isRead", SearchParamUtil.getBoolean(searchParam, "IS_READ"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<Notification> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT n FROM Notification n
            WHERE (:id IS NULL OR n.id = :id)
            AND (:userId IS NULL OR n.user.id = :userId)
            AND (:title IS NULL OR LOWER(n.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND (:notificationType IS NULL OR n.notificationType = :notificationType)
            AND (:isRead IS NULL OR n.isRead = :isRead)
            AND (:isActive IS NULL OR n.isActive = :isActive)
            AND (:isDeleted IS NULL OR n.isDeleted = :isDeleted)
            ORDER BY n.createdAt DESC
            """, Notification.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("userId", SearchParamUtil.getInteger(searchParam, "USER_ID"))
                .setParameter("title", SearchParamUtil.getString(searchParam, "TITLE"))
                .setParameter("notificationType", SearchParamUtil.getString(searchParam, "NOTIFICATION_TYPE"))
                .setParameter("isRead", SearchParamUtil.getBoolean(searchParam, "IS_READ"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}