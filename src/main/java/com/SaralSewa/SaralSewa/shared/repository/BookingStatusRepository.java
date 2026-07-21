package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Booking;
import com.SaralSewa.SaralSewa.shared.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingStatusRepository extends JpaRepository<Booking, Integer> {

   Status findByCode(String code);

    Optional<Booking>  findByCodeAndIsDeletedFalse(String code);

    boolean existsByCode(String code);

    List<Booking> findByIsActiveTrue();

    List<Booking> findByIsActiveTrueAndIsDeletedFalse();
}