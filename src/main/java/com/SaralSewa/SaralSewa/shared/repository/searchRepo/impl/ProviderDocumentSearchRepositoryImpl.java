package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.ProviderDocument;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.ProviderDocumentSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProviderDocumentSearchRepositoryImpl implements ProviderDocumentSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(pd) FROM ProviderDocument pd
            WHERE (:id IS NULL OR pd.id = :id)
            AND (:providerId IS NULL OR pd.provider.id = :providerId)
            AND (:documentName IS NULL OR LOWER(pd.documentName) LIKE LOWER(CONCAT('%', :documentName, '%')))
            AND (:documentType IS NULL OR pd.documentType = :documentType)
            AND (:verificationStatus IS NULL OR pd.verificationStatus = :verificationStatus)
            AND (:isActive IS NULL OR pd.isActive = :isActive)
            AND (:isDeleted IS NULL OR pd.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("documentName", SearchParamUtil.getString(searchParam, "DOCUMENT_NAME"))
                .setParameter("documentType", SearchParamUtil.getString(searchParam, "DOCUMENT_TYPE"))
                .setParameter("verificationStatus", SearchParamUtil.getString(searchParam, "VERIFICATION_STATUS"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<ProviderDocument> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT pd FROM ProviderDocument pd
            WHERE (:id IS NULL OR pd.id = :id)
            AND (:providerId IS NULL OR pd.provider.id = :providerId)
            AND (:documentName IS NULL OR LOWER(pd.documentName) LIKE LOWER(CONCAT('%', :documentName, '%')))
            AND (:documentType IS NULL OR pd.documentType = :documentType)
            AND (:verificationStatus IS NULL OR pd.verificationStatus = :verificationStatus)
            AND (:isActive IS NULL OR pd.isActive = :isActive)
            AND (:isDeleted IS NULL OR pd.isDeleted = :isDeleted)
            ORDER BY pd.id ASC
            """, ProviderDocument.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("documentName", SearchParamUtil.getString(searchParam, "DOCUMENT_NAME"))
                .setParameter("documentType", SearchParamUtil.getString(searchParam, "DOCUMENT_TYPE"))
                .setParameter("verificationStatus", SearchParamUtil.getString(searchParam, "VERIFICATION_STATUS"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}