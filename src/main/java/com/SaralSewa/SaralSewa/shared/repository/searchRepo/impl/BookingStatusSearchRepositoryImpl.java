package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.Booking;
import com.SaralSewa.SaralSewa.shared.entity.BookingStatus;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.BookingStatusSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor
public class BookingStatusSearchRepositoryImpl implements BookingStatusSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(bs) FROM BookingStatus bs
            WHERE (:id IS NULL OR bs.id = :id)
            AND (:name IS NULL OR LOWER(bs.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(bs.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:isActive IS NULL OR bs.isActive = :isActive)
            AND (:isDeleted IS NULL OR bs.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("name", SearchParamUtil.getString(searchParam, "NAME"))
                .setParameter("code", SearchParamUtil.getString(searchParam, "CODE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<BookingStatus> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT bs FROM BookingStatus bs
            WHERE (:id IS NULL OR bs.id = :id)
            AND (:name IS NULL OR LOWER(bs.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(bs.code) LIKE LOWER(CONCAT('%', :code, '%')))
            AND (:isActive IS NULL OR bs.isActive = :isActive)
            AND (:isDeleted IS NULL OR bs.isDeleted = :isDeleted)
            ORDER BY bs.name ASC
            """, BookingStatus.class)
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