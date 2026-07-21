package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;


import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.ProviderSkill;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.ProviderSkillSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProviderSkillSearchRepositoryImpl implements ProviderSkillSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(ps) FROM ProviderSkill ps
            WHERE (:id IS NULL OR ps.id = :id)
            AND (:providerId IS NULL OR ps.provider.id = :providerId)
            AND (:skillName IS NULL OR LOWER(ps.skillName) LIKE LOWER(CONCAT('%', :skillName, '%')))
            AND (:isActive IS NULL OR ps.isActive = :isActive)
            AND (:isDeleted IS NULL OR ps.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("skillName", SearchParamUtil.getString(searchParam, "SKILL_NAME"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<ProviderSkill> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT ps FROM ProviderSkill ps
            WHERE (:id IS NULL OR ps.id = :id)
            AND (:providerId IS NULL OR ps.provider.id = :providerId)
            AND (:skillName IS NULL OR LOWER(ps.skillName) LIKE LOWER(CONCAT('%', :skillName, '%')))
            AND (:isActive IS NULL OR ps.isActive = :isActive)
            AND (:isDeleted IS NULL OR ps.isDeleted = :isDeleted)
            ORDER BY ps.skillName ASC
            """, ProviderSkill.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("providerId", SearchParamUtil.getInteger(searchParam, "PROVIDER_ID"))
                .setParameter("skillName", SearchParamUtil.getString(searchParam, "SKILL_NAME"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}