package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ProviderProfile;
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
public interface ProviderProfileRepository extends JpaRepository<ProviderProfile, Integer> {

    ProviderProfile findByProviderId(Integer providerId);

    ProviderProfile findByProviderIdAndIsDeletedFalse(Integer providerId);

    boolean existsByProviderId(Integer providerId);

    Page<ProviderProfile> findByCityContainingIgnoreCase(String city, Pageable pageable);

    Page<ProviderProfile> findByDistrictContainingIgnoreCase(String district, Pageable pageable);

    List<ProviderProfile> findByIsActiveTrue();

    List<ProviderProfile> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE ProviderProfile p SET p.isDeleted = true, p.deletedAt = CURRENT_TIMESTAMP WHERE p.id = :id")
    void softDeleteById(@Param("id") Integer id);
}