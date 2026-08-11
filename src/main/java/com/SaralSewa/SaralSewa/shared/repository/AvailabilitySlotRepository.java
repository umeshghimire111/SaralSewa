package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.AvailabilitySlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, Integer> {

    List<AvailabilitySlot> findByProviderId(Integer providerId);

    Page<AvailabilitySlot> findByProviderId(Integer providerId, Pageable pageable);

    List<AvailabilitySlot> findByProviderIdAndIsDeletedFalse(Integer providerId);

    List<AvailabilitySlot> findByProviderIdAndAvailableDate(Integer providerId, LocalDate date);

    List<AvailabilitySlot> findByProviderIdAndIsBookedFalse(Integer providerId);

    List<AvailabilitySlot> findByIsActiveTrue();

    List<AvailabilitySlot> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE AvailabilitySlot s SET s.isDeleted = true, s.deletedAt = CURRENT_TIMESTAMP WHERE s.id = :id")
    void softDeleteById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE AvailabilitySlot s SET s.isBooked = true, s.updatedAt = CURRENT_TIMESTAMP WHERE s.id = :id")
    void markAsBooked(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE AvailabilitySlot s SET s.isBooked = false, s.updatedAt = CURRENT_TIMESTAMP WHERE s.id = :id")
    void markAsAvailable(@Param("id") Integer id);
}