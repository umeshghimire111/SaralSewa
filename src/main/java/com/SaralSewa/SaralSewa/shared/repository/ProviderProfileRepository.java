package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ProviderProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderProfileRepository extends JpaRepository<ProviderProfile, Integer> {

    ProviderProfile findByProviderId(Integer providerId);

    ProviderProfile findByProviderIdAndIsDeletedFalse(Integer providerId);

    boolean existsByProviderId(Integer providerId);

    List<ProviderProfile> findByCityContainingIgnoreCase(String city);

    List<ProviderProfile> findByDistrictContainingIgnoreCase(String district);

    List<ProviderProfile> findByIsActiveTrue();

    List<ProviderProfile> findByIsActiveTrueAndIsDeletedFalse();
}