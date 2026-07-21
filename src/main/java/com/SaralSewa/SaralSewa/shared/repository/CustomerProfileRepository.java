package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.CustomerProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfiles, Integer> {

    CustomerProfiles findByUserId(Integer userId);

    CustomerProfiles findByUserIdAndIsDeletedFalse(Integer userId);

    boolean existsByUserId(Integer userId);

    List<CustomerProfiles> findByIsActiveTrue();

    List<CustomerProfiles> findByIsActiveTrueAndIsDeletedFalse();
}