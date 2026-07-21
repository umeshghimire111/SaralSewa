package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findByBookingCode(String bookingCode);

    Booking findByBookingCodeAndIsDeletedFalse(String bookingCode);

    boolean existsByBookingCode(String bookingCode);

    List<Booking> findByCustomerId(Integer customerId);

    List<Booking> findByCustomerIdAndIsActiveTrue(Integer customerId);

    List<Booking> findByCustomerIdAndBookingStatusCode(Integer customerId, String statusCode);

    List<Booking> findByProviderId(Integer providerId);

    List<Booking> findByProviderIdAndIsActiveTrue(Integer providerId);

    List<Booking> findByProviderIdAndBookingStatusCode(Integer providerId, String statusCode);

    List<Booking> findByBookingStatusCode(String statusCode);

    List<Booking> findByBookingStatusCodeAndIsActiveTrue(String statusCode);
}