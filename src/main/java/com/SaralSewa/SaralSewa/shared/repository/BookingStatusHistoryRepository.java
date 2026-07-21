package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.BookingStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingStatusHistoryRepository extends JpaRepository<BookingStatusHistory, Integer> {

    List<BookingStatusHistory> findByBookingId(Integer bookingId);

    List<BookingStatusHistory> findByBookingIdOrderByCreatedAtDesc(Integer bookingId);
}