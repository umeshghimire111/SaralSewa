package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

    Service findByCode(String code);

    Service findByCodeAndIsDeletedFalse(String code);

    boolean existsByCode(String code);

    List<Service> findByCategoryId(Integer categoryId);

    List<Service> findByCategoryIdAndIsActiveTrue(Integer categoryId);

    List<Service> findByNameContainingIgnoreCase(String name);

    List<Service> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);

    List<Service> findByIsActiveTrue();

    List<Service> findByIsActiveTrueAndIsDeletedFalse();
}