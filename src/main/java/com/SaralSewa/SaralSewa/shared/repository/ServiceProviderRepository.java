package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Integer> {

    ServiceProvider findByUserId(Integer userId);

    ServiceProvider findByUserIdAndIsDeletedFalse(Integer userId);

    boolean existsByUserId(Integer userId);

    List<ServiceProvider> findByProfessionContainingIgnoreCase(String profession);

    List<ServiceProvider> findByProfessionAndIsActiveTrue(String profession);

    List<ServiceProvider> findByApprovalStatusCode(String statusCode);

    List<ServiceProvider> findByApprovalStatusCodeAndIsActiveTrue(String statusCode);

    List<ServiceProvider> findByApprovalStatusCodeAndIsDeletedFalse(String statusCode);

    List<ServiceProvider> findByIsActiveTrue();

    List<ServiceProvider> findByIsActiveTrueAndIsDeletedFalse();
}