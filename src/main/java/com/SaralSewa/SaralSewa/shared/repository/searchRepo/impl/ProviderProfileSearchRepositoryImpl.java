package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;


import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.ProviderProfile;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.ProviderProfileSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProviderProfileSearchRepositoryImpl implements ProviderProfileSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(pp) FROM ProviderProfile pp
            WHERE (:id IS NULL OR pp.id = :id)
            AND (:providerId IS NULL OR pp.provider.id = :providerId)
            AND (:city IS NULL OR LOWER(pp.city) LIKE LOWER(CONCAT('%', :city, '%')))
            AND (:district IS NULL OR LOWER(pp.district) LIKE LOWER(CONCAT('%', :district, '%')))
            AND (:minRate IS NULL OR pp.hourlyRate >= :minRate)
            AND (:maxRate IS NULL OR pp.hourlyRate <= :maxRate)
            AND (:isActive IS NULL OR pp.isActive = :isActive)
            AND (:isDeleted IS NULL OR pp.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("city", SearchParamUtil.getString(searchParam, "CITY"))
                .setParameter("district", SearchParamUtil.getString(searchParam, "DISTRICT"))
                .setParameter("minRate", SearchParamUtil.getBigDecimal(searchParam, "MIN_RATE"))
                .setParameter("maxRate", SearchParamUtil.getBigDecimal(searchParam, "MAX_RATE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<ProviderProfile> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT pp FROM ProviderProfile pp
            WHERE (:id IS NULL OR pp.id = :id)
            AND (:providerId IS NULL OR pp.provider.id = :providerId)
            AND (:city IS NULL OR LOWER(pp.city) LIKE LOWER(CONCAT('%', :city, '%')))
            AND (:district IS NULL OR LOWER(pp.district) LIKE LOWER(CONCAT('%', :district, '%')))
            AND (:minRate IS NULL OR pp.hourlyRate >= :minRate)
            AND (:maxRate IS NULL OR pp.hourlyRate <= :maxRate)
            AND (:isActive IS NULL OR pp.isActive = :isActive)
            AND (:isDeleted IS NULL OR pp.isDeleted = :isDeleted)
            ORDER BY pp.id ASC
            """, ProviderProfile.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("city", SearchParamUtil.getString(searchParam, "CITY"))
                .setParameter("district", SearchParamUtil.getString(searchParam, "DISTRICT"))
                .setParameter("minRate", SearchParamUtil.getBigDecimal(searchParam, "MIN_RATE"))
                .setParameter("maxRate", SearchParamUtil.getBigDecimal(searchParam, "MAX_RATE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}