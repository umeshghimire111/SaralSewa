package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;


import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.Role;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.RoleSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RoleSearchRepositoryImpl implements RoleSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(r) FROM Role r
            WHERE (:id IS NULL OR r.id = :id)
            AND (:name IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(r.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:isActive IS NULL OR r.isActive = :isActive)
            AND (:isDeleted IS NULL OR r.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("name", SearchParamUtil.getString(searchParam, "NAME"))
                .setParameter("code", SearchParamUtil.getString(searchParam, "CODE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<Role> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT r FROM Role r
            WHERE (:id IS NULL OR r.id = :id)
            AND (:name IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(r.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:isActive IS NULL OR r.isActive = :isActive)
            AND (:isDeleted IS NULL OR r.isDeleted = :isDeleted)
            ORDER BY r.name ASC
            """, Role.class)
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