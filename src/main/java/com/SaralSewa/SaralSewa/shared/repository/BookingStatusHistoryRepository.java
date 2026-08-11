package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.BookingStatusHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingStatusHistoryRepository extends JpaRepository<BookingStatusHistory, Integer> {

    List<BookingStatusHistory> findByBookingId(Integer bookingId);

    Page<BookingStatusHistory> findByBookingId(Integer bookingId, Pageable pageable);

    List<BookingStatusHistory> findByBookingIdOrderByCreatedAtDesc(Integer bookingId);
}