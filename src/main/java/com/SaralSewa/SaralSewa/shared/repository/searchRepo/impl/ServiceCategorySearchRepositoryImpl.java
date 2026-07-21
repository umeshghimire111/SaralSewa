package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.ServiceCategory;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.ServiceCategorySearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ServiceCategorySearchRepositoryImpl implements ServiceCategorySearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(sc) FROM ServiceCategory sc
            WHERE (:id IS NULL OR sc.id = :id)
            AND (:name IS NULL OR LOWER(sc.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(sc.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:isActive IS NULL OR sc.isActive = :isActive)
            AND (:isDeleted IS NULL OR sc.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("name", SearchParamUtil.getString(searchParam, "NAME"))
                .setParameter("code", SearchParamUtil.getString(searchParam, "CODE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<ServiceCategory> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT sc FROM ServiceCategory sc
            WHERE (:id IS NULL OR sc.id = :id)
            AND (:name IS NULL OR LOWER(sc.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(sc.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:isActive IS NULL OR sc.isActive = :isActive)
            AND (:isDeleted IS NULL OR sc.isDeleted = :isDeleted)
            ORDER BY sc.name ASC
            """, ServiceCategory.class)
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
