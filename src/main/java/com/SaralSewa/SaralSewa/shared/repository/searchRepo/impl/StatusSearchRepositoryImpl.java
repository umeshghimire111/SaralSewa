package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;


import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.Status;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.StatusSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class StatusSearchRepositoryImpl implements StatusSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(s) FROM Status s
            WHERE (:id IS NULL OR s.id = :id)
            AND (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(s.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:isActive IS NULL OR s.isActive = :isActive)
            AND (:isDeleted IS NULL OR s.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("name", SearchParamUtil.getString(searchParam, "NAME"))
                .setParameter("code", SearchParamUtil.getString(searchParam, "CODE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<Status> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT s FROM Status s
            WHERE (:id IS NULL OR s.id = :id)
            AND (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(s.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:isActive IS NULL OR s.isActive = :isActive)
            AND (:isDeleted IS NULL OR s.isDeleted = :isDeleted)
            ORDER BY s.name ASC
            """, Status.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("name", SearchParamUtil.getString(searchParam, "NAME"))
                .setParameter("code", SearchParamUtil.getString(searchParam, "CODE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}