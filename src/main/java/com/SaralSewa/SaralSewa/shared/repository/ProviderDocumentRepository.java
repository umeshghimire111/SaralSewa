package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ProviderDocument;
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
public interface ProviderDocumentRepository extends JpaRepository<ProviderDocument, Integer> {

    List<ProviderDocument> findByProviderId(Integer providerId);

    Page<ProviderDocument> findByProviderId(Integer providerId, Pageable pageable);

    List<ProviderDocument> findByProviderIdAndIsDeletedFalse(Integer providerId);

    List<ProviderDocument> findByDocumentType(String documentType);

    Page<ProviderDocument> findByDocumentType(String documentType, Pageable pageable);

    List<ProviderDocument> findByVerificationStatus(String status);

    Page<ProviderDocument> findByVerificationStatus(String status, Pageable pageable);

    List<ProviderDocument> findByIsActiveTrue();

    List<ProviderDocument> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE ProviderDocument d SET d.isDeleted = true, d.deletedAt = CURRENT_TIMESTAMP WHERE d.id = :id")
    void softDeleteById(@Param("id") Integer id);
}