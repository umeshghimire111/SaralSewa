package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;


import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.Review;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.ReviewSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ReviewSearchRepositoryImpl implements ReviewSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(r) FROM Review r
            WHERE (:id IS NULL OR r.id = :id)
            AND (:bookingId IS NULL OR r.booking.id = :bookingId)
            AND (:customerId IS NULL OR r.customer.id = :customerId)
            AND (:providerId IS NULL OR r.provider.id = :providerId)
            AND (:minRating IS NULL OR r.rating >= :minRating)
            AND (:maxRating IS NULL OR r.rating <= :maxRating)
            AND (:isActive IS NULL OR r.isActive = :isActive)
            AND (:isDeleted IS NULL OR r.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("bookingId", SearchParamUtil.getInteger(searchParam, "BOOKING_ID"))
                .setParameter("customerId", SearchParamUtil.getInteger(searchParam, "CUSTOMER_ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("minRating", SearchParamUtil.getInteger(searchParam, "MIN_RATING"))
                .setParameter("maxRating", SearchParamUtil.getInteger(searchParam, "MAX_RATING"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<Review> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT r FROM Review r
            WHERE (:id IS NULL OR r.id = :id)
            AND (:bookingId IS NULL OR r.booking.id = :bookingId)
            AND (:customerId IS NULL OR r.customer.id = :customerId)
            AND (:providerId IS NULL OR r.provider.id = :providerId)
            AND (:minRating IS NULL OR r.rating >= :minRating)
            AND (:maxRating IS NULL OR r.rating <= :maxRating)
            AND (:isActive IS NULL OR r.isActive = :isActive)
            AND (:isDeleted IS NULL OR r.isDeleted = :isDeleted)
            ORDER BY r.createdAt DESC
            """, Review.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("bookingId", SearchParamUtil.getInteger(searchParam, "BOOKING_ID"))
                .setParameter("customerId", SearchParamUtil.getInteger(searchParam, "CUSTOMER_ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("minRating", SearchParamUtil.getInteger(searchParam, "MIN_RATING"))
                .setParameter("maxRating", SearchParamUtil.getInteger(searchParam, "MAX_RATING"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}