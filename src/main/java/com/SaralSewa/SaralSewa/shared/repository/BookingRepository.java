package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findByBookingCode(String bookingCode);

    Booking findByBookingCodeAndIsDeletedFalse(String bookingCode);

    boolean existsByBookingCode(String bookingCode);

    List<Booking> findByCustomerId(Integer customerId);

    Page<Booking> findByCustomerId(Integer customerId, Pageable pageable);

    List<Booking> findByCustomerIdAndIsActiveTrue(Integer customerId);

    List<Booking> findByCustomerIdAndBookingStatusCode(Integer customerId, String statusCode);

    List<Booking> findByProviderId(Integer providerId);

    Page<Booking> findByProviderId(Integer providerId, Pageable pageable);

    List<Booking> findByProviderIdAndIsActiveTrue(Integer providerId);

    List<Booking> findByProviderIdAndBookingStatusCode(Integer providerId, String statusCode);

    List<Booking> findByBookingStatusCode(String statusCode);

    Page<Booking> findByBookingStatusCode(String statusCode, Pageable pageable);

    List<Booking> findByBookingStatusCodeAndIsActiveTrue(String statusCode);

    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.isDeleted = true, b.deletedAt = CURRENT_TIMESTAMP WHERE b.id = :id")
    void softDeleteById(@Param("id") Integer id);
}