package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.AvailabilitySlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, Integer> {

    List<AvailabilitySlot> findByProviderId(Integer providerId);

    List<AvailabilitySlot> findByProviderIdAndIsDeletedFalse(Integer providerId);

    List<AvailabilitySlot> findByProviderIdAndAvailableDate(Integer providerId, LocalDate date);

    List<AvailabilitySlot> findByProviderIdAndIsBookedFalse(Integer providerId);

    List<AvailabilitySlot> findByIsActiveTrue();

    List<AvailabilitySlot> findByIsActiveTrueAndIsDeletedFalse();
}