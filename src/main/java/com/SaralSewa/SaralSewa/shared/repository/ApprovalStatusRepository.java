package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ApprovalStatusRepository extends JpaRepository<ApprovalStatus, Integer> {

    ApprovalStatus findByCode(String code);

    ApprovalStatus findByCodeAndIsDeletedFalse(String code);

    boolean existsByCode(String code);

    List<ApprovalStatus> findByIsActiveTrue();

    List<ApprovalStatus> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE ApprovalStatus a SET a.isDeleted = true, a.deletedAt = CURRENT_TIMESTAMP WHERE a.id = :id")
    void softDeleteById(@Param("id") Integer id);
}