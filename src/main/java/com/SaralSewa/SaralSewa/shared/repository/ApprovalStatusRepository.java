package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalStatusRepository extends JpaRepository<ApprovalStatus, Integer> {

    ApprovalStatus findByCode(String code);

    ApprovalStatus findByCodeAndIsDeletedFalse(String code);

    boolean existsByCode(String code);

    List<ApprovalStatus> findByIsActiveTrue();

    List<ApprovalStatus> findByIsActiveTrueAndIsDeletedFalse();
}