package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ProviderSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderSkillRepository extends JpaRepository<ProviderSkill, Integer> {

    List<ProviderSkill> findByProviderId(Integer providerId);

    List<ProviderSkill> findByProviderIdAndIsDeletedFalse(Integer providerId);

    List<ProviderSkill> findBySkillNameContainingIgnoreCase(String skillName);

    List<ProviderSkill> findByIsActiveTrue();

    List<ProviderSkill> findByIsActiveTrueAndIsDeletedFalse();
}