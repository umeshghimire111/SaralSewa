package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.Service;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.ServiceSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor

public class ServiceSearchRepositoryImpl implements ServiceSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(s) FROM Service s
            WHERE (:id IS NULL OR s.id = :id)
            AND (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(s.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:categoryId IS NULL OR s.category.id = :categoryId)
            AND (:minPrice IS NULL OR s.basePrice >= :minPrice)
            AND (:maxPrice IS NULL OR s.basePrice <= :maxPrice)
            AND (:isActive IS NULL OR s.isActive = :isActive)
            AND (:isDeleted IS NULL OR s.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("name", SearchParamUtil.getString(searchParam, "NAME"))
                .setParameter("code", SearchParamUtil.getString(searchParam, "CODE"))
                .setParameter("categoryId", SearchParamUtil.getInteger(searchParam, "CATEGORY_ID"))
                .setParameter("minPrice", SearchParamUtil.getBigDecimal(searchParam, "MIN_PRICE"))
                .setParameter("maxPrice", SearchParamUtil.getBigDecimal(searchParam, "MAX_PRICE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<Service> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT s FROM Service s
            WHERE (:id IS NULL OR s.id = :id)
            AND (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(s.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:categoryId IS NULL OR s.category.id = :categoryId)
            AND (:minPrice IS NULL OR s.basePrice >= :minPrice)
            AND (:maxPrice IS NULL OR s.basePrice <= :maxPrice)
            AND (:isActive IS NULL OR s.isActive = :isActive)
            AND (:isDeleted IS NULL OR s.isDeleted = :isDeleted)
            ORDER BY s.name ASC
            """, Service.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("name", SearchParamUtil.getString(searchParam, "NAME"))
                .setParameter("code", SearchParamUtil.getString(searchParam, "CODE"))
                .setParameter("categoryId", SearchParamUtil.getInteger(searchParam, "CATEGORY_ID"))
                .setParameter("minPrice", SearchParamUtil.getBigDecimal(searchParam, "MIN_PRICE"))
                .setParameter("maxPrice", SearchParamUtil.getBigDecimal(searchParam, "MAX_PRICE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}