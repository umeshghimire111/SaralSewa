package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ProviderDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderDocumentRepository extends JpaRepository<ProviderDocument, Integer> {

    List<ProviderDocument> findByProviderId(Integer providerId);

    List<ProviderDocument> findByProviderIdAndIsDeletedFalse(Integer providerId);

    List<ProviderDocument> findByDocumentType(String documentType);

    List<ProviderDocument> findByVerificationStatus(String status);

    List<ProviderDocument> findByIsActiveTrue();

    List<ProviderDocument> findByIsActiveTrueAndIsDeletedFalse();
}