package com.SaralSewa.SaralSewa.shared.repository.searchRepo.impl;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.core.util.SearchParamUtil;
import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.repository.searchRepo.UserSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserSearchRepositoryImpl implements UserSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return em.createQuery("""
            SELECT COUNT(u) FROM User u
            WHERE (:id IS NULL OR u.id = :id)
            AND (:firstName IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')))
            AND (:lastName IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lastName, '%')))
            AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%')))
            AND (:phone IS NULL OR LOWER(u.phone) LIKE LOWER(CONCAT('%', :phone, '%')))
            AND (:roleCode IS NULL OR u.role.code = :roleCode)
            AND (:statusCode IS NULL OR u.status.code = :statusCode)
            AND (:isActive IS NULL OR u.isActive = :isActive)
            AND (:isDeleted IS NULL OR u.isDeleted = :isDeleted)
            """, Long.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("firstName", SearchParamUtil.getString(searchParam, "FIRST_NAME"))
                .setParameter("lastName", SearchParamUtil.getString(searchParam, "LAST_NAME"))
                .setParameter("email", SearchParamUtil.getString(searchParam, "EMAIL"))
                .setParameter("phone", SearchParamUtil.getString(searchParam, "PHONE"))
                .setParameter("roleCode", SearchParamUtil.getString(searchParam, "ROLE_CODE"))
                .setParameter("statusCode", SearchParamUtil.getString(searchParam, "STATUS_CODE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .getSingleResult();
    }

    @Override
    public List<User> getAll(SearchParam searchParam) {
        return em.createQuery("""
            SELECT u FROM User u
            WHERE (:id IS NULL OR u.id = :id)
            AND (:firstName IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')))
            AND (:lastName IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lastName, '%')))
            AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%')))
            AND (:phone IS NULL OR LOWER(u.phone) LIKE LOWER(CONCAT('%', :phone, '%')))
            AND (:roleCode IS NULL OR u.role.code = :roleCode)
            AND (:statusCode IS NULL OR u.status.code = :statusCode)
            AND (:isActive IS NULL OR u.isActive = :isActive)
            AND (:isDeleted IS NULL OR u.isDeleted = :isDeleted)
            ORDER BY u.id ASC
            """, User.class)
                .setParameter("id", SearchParamUtil.getInteger(searchParam, "ID"))
                .setParameter("firstName", SearchParamUtil.getString(searchParam, "FIRST_NAME"))
                .setParameter("lastName", SearchParamUtil.getString(searchParam, "LAST_NAME"))
                .setParameter("email", SearchParamUtil.getString(searchParam, "EMAIL"))
                .setParameter("phone", SearchParamUtil.getString(searchParam, "PHONE"))
                .setParameter("roleCode", SearchParamUtil.getString(searchParam, "ROLE_CODE"))
                .setParameter("statusCode", SearchParamUtil.getString(searchParam, "STATUS_CODE"))
                .setParameter("isActive", SearchParamUtil.getBoolean(searchParam, "IS_ACTIVE"))
                .setParameter("isDeleted", SearchParamUtil.getBoolean(searchParam, "IS_DELETED"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}