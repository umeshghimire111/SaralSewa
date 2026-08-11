package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ProviderSkill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderSkillRepository extends JpaRepository<ProviderSkill, Integer> {

    List<ProviderSkill> findByProviderId(Integer providerId);

    Page<ProviderSkill> findByProviderId(Integer providerId, Pageable pageable);

    List<ProviderSkill> findByProviderIdAndIsDeletedFalse(Integer providerId);

    List<ProviderSkill> findBySkillNameContainingIgnoreCase(String skillName);

    List<ProviderSkill> findByIsActiveTrue();

    List<ProviderSkill> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Query("UPDATE ProviderSkill s SET s.isDeleted = true WHERE s.id = :id")
    void softDeleteById(@Param("id") Integer id);
}
