package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {

    ServiceCategory findByCode(String code);

    ServiceCategory findByCodeAndIsDeletedFalse(String code);

    boolean existsByCode(String code);

    List<ServiceCategory> findByNameContainingIgnoreCase(String name);

    List<ServiceCategory> findByIsActiveTrue();

    List<ServiceCategory> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE ServiceCategory c SET c.isDeleted = true, c.deletedAt = CURRENT_TIMESTAMP WHERE c.id = :id")
    void softDeleteById(@Param("id") Integer id);
}