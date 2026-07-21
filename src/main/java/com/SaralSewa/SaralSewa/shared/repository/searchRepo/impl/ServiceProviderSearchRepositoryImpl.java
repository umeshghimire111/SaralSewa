package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.ServiceProvider;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.ServiceProviderSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ServiceProviderSearchRepositoryImpl implements ServiceProviderSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(sp) FROM ServiceProvider sp
            WHERE (:id IS NULL OR sp.id = :id)
            AND (:userId IS NULL OR sp.user.id = :userId)
            AND (:profession IS NULL OR LOWER(sp.profession) LIKE LOWER(CONCAT('%', :profession, '%')))
            AND (:approvalStatus IS NULL OR sp.approvalStatus.code = :approvalStatus)
            AND (:minRating IS NULL OR sp.averageRating >= :minRating)
            AND (:maxRating IS NULL OR sp.averageRating <= :maxRating)
            AND (:isActive IS NULL OR sp.isActive = :isActive)
            AND (:isDeleted IS NULL OR sp.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("userId", SearchParamUtil.getInteger(searchParam, "USER_ID"))
                .setParameter("profession", SearchParamUtil.getString(searchParam, "PROFESSION"))
                .setParameter("approvalStatus", SearchParamUtil.getString(searchParam, "APPROVAL_STATUS"))
                .setParameter("minRating", SearchParamUtil.getBigDecimal(searchParam, "MIN_RATING"))
                .setParameter("maxRating", SearchParamUtil.getBigDecimal(searchParam, "MAX_RATING"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<ServiceProvider> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT sp FROM ServiceProvider sp
            WHERE (:id IS NULL OR sp.id = :id)
            AND (:userId IS NULL OR sp.user.id = :userId)
            AND (:profession IS NULL OR LOWER(sp.profession) LIKE LOWER(CONCAT('%', :profession, '%')))
            AND (:approvalStatus IS NULL OR sp.approvalStatus.code = :approvalStatus)
            AND (:minRating IS NULL OR sp.averageRating >= :minRating)
            AND (:maxRating IS NULL OR sp.averageRating <= :maxRating)
            AND (:isActive IS NULL OR sp.isActive = :isActive)
            AND (:isDeleted IS NULL OR sp.isDeleted = :isDeleted)
            ORDER BY sp.id ASC
            """, ServiceProvider.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("userId", SearchParamUtil.getInteger(searchParam, "USER_ID"))
                .setParameter("profession", SearchParamUtil.getString(searchParam, "PROFESSION"))
                .setParameter("approvalStatus", SearchParamUtil.getString(searchParam, "APPROVAL_STATUS"))
                .setParameter("minRating", SearchParamUtil.getBigDecimal(searchParam, "MIN_RATING"))
                .setParameter("maxRating", SearchParamUtil.getBigDecimal(searchParam, "MAX_RATING"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}