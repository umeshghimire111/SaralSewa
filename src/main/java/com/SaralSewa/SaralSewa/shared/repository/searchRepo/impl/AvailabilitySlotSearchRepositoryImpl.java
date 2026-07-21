package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.AvailabilitySlot;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.AvailabilitySlotSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AvailabilitySlotSearchRepositoryImpl implements AvailabilitySlotSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(a) FROM AvailabilitySlot a
            WHERE (:id IS NULL OR a.id = :id)
            AND (:providerId IS NULL OR a.provider.id = :providerId)
            AND (:fromDate IS NULL OR a.availableDate >= :fromDate)
            AND (:toDate IS NULL OR a.availableDate <= :toDate)
            AND (:isBooked IS NULL OR a.isBooked = :isBooked)
            AND (:isActive IS NULL OR a.isActive = :isActive)
            AND (:isDeleted IS NULL OR a.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("fromDate", SearchParamUtil.getLocalDateTime(searchParam, "FROM_DATE"))
                .setParameter("toDate", SearchParamUtil.getLocalDateTime(searchParam, "TO_DATE"))
                .setParameter("isBooked", SearchParamUtil.getBoolean(searchParam, "IS_BOOKED"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<AvailabilitySlot> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT a FROM AvailabilitySlot a
            WHERE (:id IS NULL OR a.id = :id)
            AND (:providerId IS NULL OR a.provider.id = :providerId)
            AND (:fromDate IS NULL OR a.availableDate >= :fromDate)
            AND (:toDate IS NULL OR a.availableDate <= :toDate)
            AND (:isBooked IS NULL OR a.isBooked = :isBooked)
            AND (:isActive IS NULL OR a.isActive = :isActive)
            AND (:isDeleted IS NULL OR a.isDeleted = :isDeleted)
            ORDER BY a.availableDate ASC, a.startTime ASC
            """, AvailabilitySlot.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("fromDate", SearchParamUtil.getLocalDateTime(searchParam, "FROM_DATE"))
                .setParameter("toDate", SearchParamUtil.getLocalDateTime(searchParam, "TO_DATE"))
                .setParameter("isBooked", SearchParamUtil.getBoolean(searchParam, "IS_BOOKED"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}