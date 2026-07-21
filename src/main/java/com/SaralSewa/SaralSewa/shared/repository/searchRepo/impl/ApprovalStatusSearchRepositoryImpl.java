package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;


import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.ApprovalStatus;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.ApprovalStatusSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ApprovalStatusSearchRepositoryImpl implements ApprovalStatusSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(as_) FROM ApprovalStatus as_
            WHERE (:id IS NULL OR as_.id = :id)
            AND (:name IS NULL OR LOWER(as_.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(as_.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:isDefault IS NULL OR as_.isDefault = :isDefault)
            AND (:isActive IS NULL OR as_.isActive = :isActive)
            AND (:isDeleted IS NULL OR as_.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("name", SearchParamUtil.getString(searchParam, "NAME"))
                .setParameter("code", SearchParamUtil.getString(searchParam, "CODE"))
                .setParameter("isDefault", SearchParamUtil.getBoolean(searchParam, "IS_DEFAULT"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<ApprovalStatus> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT as_ FROM ApprovalStatus as_
            WHERE (:id IS NULL OR as_.id = :id)
            AND (:name IS NULL OR LOWER(as_.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(as_.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:isDefault IS NULL OR as_.isDefault = :isDefault)
            AND (:isActive IS NULL OR as_.isActive = :isActive)
            AND (:isDeleted IS NULL OR as_.isDeleted = :isDeleted)
            ORDER BY as_.name ASC
            """, ApprovalStatus.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("name", SearchParamUtil.getString(searchParam, "NAME"))
                .setParameter("code", SearchParamUtil.getString(searchParam, "CODE"))
                .setParameter("isDefault", SearchParamUtil.getBoolean(searchParam, "IS_DEFAULT"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}
