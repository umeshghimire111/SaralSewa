package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;


import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.CustomerProfiles;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.CustomerProfileSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CustomerProfileSearchRepositoryImpl implements CustomerProfileSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(cp) FROM CustomerProfiles cp
            WHERE (:id IS NULL OR cp.id = :id)
            AND (:userId IS NULL OR cp.user.id = :userId)
            AND (:address IS NULL OR LOWER(cp.address) LIKE LOWER(CONCAT('%', :address, '%')))
            AND (:preferredPayment IS NULL OR cp.preferredPayment = :preferredPayment)
            AND (:isActive IS NULL OR cp.isActive = :isActive)
            AND (:isDeleted IS NULL OR cp.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("userId", SearchParamUtil.getInteger(searchParam, "USER_ID"))
                .setParameter("address", SearchParamUtil.getString(searchParam, "ADDRESS"))
                .setParameter("preferredPayment", SearchParamUtil.getString(searchParam, "PREFERRED_PAYMENT"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<CustomerProfiles> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT cp FROM CustomerProfiles cp
            WHERE (:id IS NULL OR cp.id = :id)
            AND (:userId IS NULL OR cp.user.id = :userId)
            AND (:address IS NULL OR LOWER(cp.address) LIKE LOWER(CONCAT('%', :address, '%')))
            AND (:preferredPayment IS NULL OR cp.preferredPayment = :preferredPayment)
            AND (:isActive IS NULL OR cp.isActive = :isActive)
            AND (:isDeleted IS NULL OR cp.isDeleted = :isDeleted)
            ORDER BY cp.id ASC
            """, CustomerProfiles.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("userId", SearchParamUtil.getInteger(searchParam, "USER_ID"))
                .setParameter("address", SearchParamUtil.getString(searchParam, "ADDRESS"))
                .setParameter("preferredPayment", SearchParamUtil.getString(searchParam, "PREFERRED_PAYMENT"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}
