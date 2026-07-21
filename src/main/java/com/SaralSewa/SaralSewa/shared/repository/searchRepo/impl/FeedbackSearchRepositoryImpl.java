package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.Feedback;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.FeedbackSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class FeedbackSearchRepositoryImpl implements FeedbackSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(f) FROM Feedback f
            WHERE (:id IS NULL OR f.id = :id)
            AND (:userId IS NULL OR f.user.id = :userId)
            AND (:subject IS NULL OR LOWER(f.subject) LIKE LOWER(CONCAT('%', :subject, '%')))
            AND (:feedbackType IS NULL OR f.feedbackType = :feedbackType)
            AND (:isActive IS NULL OR f.isActive = :isActive)
            AND (:isDeleted IS NULL OR f.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("userId", SearchParamUtil.getInteger(searchParam, "USER_ID"))
                .setParameter("subject", SearchParamUtil.getString(searchParam, "SUBJECT"))
                .setParameter("feedbackType", SearchParamUtil.getString(searchParam, "FEEDBACK_TYPE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<Feedback> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT f FROM Feedback f
            WHERE (:id IS NULL OR f.id = :id)
            AND (:userId IS NULL OR f.user.id = :userId)
            AND (:subject IS NULL OR LOWER(f.subject) LIKE LOWER(CONCAT('%', :subject, '%')))
            AND (:feedbackType IS NULL OR f.feedbackType = :feedbackType)
            AND (:isActive IS NULL OR f.isActive = :isActive)
            AND (:isDeleted IS NULL OR f.isDeleted = :isDeleted)
            ORDER BY f.createdAt DESC
            """, Feedback.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("userId", SearchParamUtil.getInteger(searchParam, "USER_ID"))
                .setParameter("subject", SearchParamUtil.getString(searchParam, "SUBJECT"))
                .setParameter("feedbackType", SearchParamUtil.getString(searchParam, "FEEDBACK_TYPE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}