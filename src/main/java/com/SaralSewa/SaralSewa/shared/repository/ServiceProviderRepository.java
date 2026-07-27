package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Integer> {

    @Query("SELECT sp FROM ServiceProvider sp WHERE sp.user.id = :userId")
    Optional<ServiceProvider> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT sp FROM ServiceProvider sp WHERE sp.user.id = :userId AND sp.isDeleted = false")
    Optional<ServiceProvider> findByUserIdAndIsDeletedFalse(@Param("userId") Integer userId);

    boolean existsByUserId(Integer userId);

    List<ServiceProvider> findByProfessionContainingIgnoreCase(String profession);

    List<ServiceProvider> findByProfessionAndIsActiveTrue(String profession);

    List<ServiceProvider> findByApprovalStatusCode(String statusCode);

    List<ServiceProvider> findByApprovalStatusCodeAndIsActiveTrue(String statusCode);

    List<ServiceProvider> findByApprovalStatusCodeAndIsDeletedFalse(String statusCode);

    List<ServiceProvider> findByIsActiveTrue();

    List<ServiceProvider> findByIsActiveTrueAndIsDeletedFalse();

    @Query("SELECT sp FROM ServiceProvider sp WHERE sp.approvalStatus.code = 'VERIFIED' AND sp.isActive = true AND sp.isDeleted = false")
    List<ServiceProvider> findApprovedProviders();

    @Query("SELECT sp FROM ServiceProvider sp WHERE sp.approvalStatus.code = 'PENDING' AND sp.isDeleted = false")
    List<ServiceProvider> findPendingApprovalProviders();

    @Query("SELECT COUNT(sp) FROM ServiceProvider sp WHERE sp.isActive = true AND sp.isDeleted = false")
    long countActiveProviders();

    @Query("SELECT COUNT(sp) FROM ServiceProvider sp WHERE sp.approvalStatus.code = 'VERIFIED' AND sp.isDeleted = false")
    long countVerifiedProviders();


    @Modifying
    @Transactional
    @Query("UPDATE ServiceProvider sp SET sp.isDeleted = true WHERE sp.id = :id")
    void softDeleteById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE ServiceProvider sp SET sp.isDeleted = false WHERE sp.id = :id")
    void restoreById(@Param("id") Integer id);
}