package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Service;
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
public interface ServiceRepository extends JpaRepository<Service, Integer> {

    Service findByCode(String code);

    Service findByCodeAndIsDeletedFalse(String code);

    boolean existsByCode(String code);

    List<Service> findByCategoryId(Integer categoryId);

    Page<Service> findByCategoryId(Integer categoryId, Pageable pageable);

    List<Service> findByCategoryIdAndIsActiveTrue(Integer categoryId);

    List<Service> findByNameContainingIgnoreCase(String name);

    List<Service> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);

    List<Service> findByIsActiveTrue();

    List<Service> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Service s SET s.isDeleted = true, s.deletedAt = CURRENT_TIMESTAMP WHERE s.id = :id")
    void softDeleteById(@Param("id") Integer id);
}