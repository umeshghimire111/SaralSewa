package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.Booking;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.BookingSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class BookingSearchRepositoryImpl implements BookingSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(b) FROM Booking b
            WHERE (:id IS NULL OR b.id = :id)
            AND (:bookingCode IS NULL OR LOWER(b.bookingCode) LIKE LOWER(CONCAT('%', :bookingCode, '%')))
            AND (:customerId IS NULL OR b.customer.id = :customerId)
            AND (:providerId IS NULL OR b.provider.id = :providerId)
            AND (:statusCode IS NULL OR b.bookingStatus.code = :statusCode)
            AND (:minAmount IS NULL OR b.totalAmount >= :minAmount)
            AND (:maxAmount IS NULL OR b.totalAmount <= :maxAmount)
            AND (:fromDate IS NULL OR b.createdAt >= :fromDate)
            AND (:toDate IS NULL OR b.createdAt <= :toDate)
            AND (:isActive IS NULL OR b.isActive = :isActive)
            AND (:isDeleted IS NULL OR b.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("bookingCode", SearchParamUtil.getString(searchParam, "BOOKING_CODE"))
                .setParameter("customerId", SearchParamUtil.getInteger(searchParam, "CUSTOMER_ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("statusCode", SearchParamUtil.getString(searchParam, "STATUS_CODE"))
                .setParameter("minAmount", SearchParamUtil.getBigDecimal(searchParam, "MIN_AMOUNT"))
                .setParameter("maxAmount", SearchParamUtil.getBigDecimal(searchParam, "MAX_AMOUNT"))
                .setParameter("fromDate", SearchParamUtil.getLocalDateTime(searchParam, "FROM_DATE"))
                .setParameter("toDate", SearchParamUtil.getLocalDateTime(searchParam, "TO_DATE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<Booking> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT b FROM Booking b
            WHERE (:id IS NULL OR b.id = :id)
            AND (:bookingCode IS NULL OR LOWER(b.bookingCode) LIKE LOWER(CONCAT('%', :bookingCode, '%')))
            AND (:customerId IS NULL OR b.customer.id = :customerId)
            AND (:providerId IS NULL OR b.provider.id = :providerId)
            AND (:statusCode IS NULL OR b.bookingStatus.code = :statusCode)
            AND (:minAmount IS NULL OR b.totalAmount >= :minAmount)
            AND (:maxAmount IS NULL OR b.totalAmount <= :maxAmount)
            AND (:fromDate IS NULL OR b.createdAt >= :fromDate)
            AND (:toDate IS NULL OR b.createdAt <= :toDate)
            AND (:isActive IS NULL OR b.isActive = :isActive)
            AND (:isDeleted IS NULL OR b.isDeleted = :isDeleted)
            ORDER BY b.createdAt DESC
            """, Booking.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("bookingCode", SearchParamUtil.getString(searchParam, "BOOKING_CODE"))
                .setParameter("customerId", SearchParamUtil.getInteger(searchParam, "CUSTOMER_ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("statusCode", SearchParamUtil.getString(searchParam, "STATUS_CODE"))
                .setParameter("minAmount", SearchParamUtil.getBigDecimal(searchParam, "MIN_AMOUNT"))
                .setParameter("maxAmount", SearchParamUtil.getBigDecimal(searchParam, "MAX_AMOUNT"))
                .setParameter("fromDate", SearchParamUtil.getLocalDateTime(searchParam, "FROM_DATE"))
                .setParameter("toDate", SearchParamUtil.getLocalDateTime(searchParam, "TO_DATE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}