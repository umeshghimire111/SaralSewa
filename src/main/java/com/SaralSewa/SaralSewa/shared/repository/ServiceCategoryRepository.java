package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {

    ServiceCategory findByCode(String code);

    ServiceCategory findByCodeAndIsDeletedFalse(String code);

    boolean existsByCode(String code);

    List<ServiceCategory> findByNameContainingIgnoreCase(String name);

    List<ServiceCategory> findByIsActiveTrue();

    List<ServiceCategory> findByIsActiveTrueAndIsDeletedFalse();
}