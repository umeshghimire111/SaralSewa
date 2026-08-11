package com.SaralSewa.SaralSewa.shared.repository;


import com.SaralSewa.SaralSewa.shared.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findByName(String name);

    Status findByCode(String code);

    boolean existsByCode(String code);

    List<Status> findByIsActiveTrue();

    List<Status> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Status s SET s.isDeleted = true, s.deletedAt = CURRENT_TIMESTAMP WHERE s.id = :id")
    void softDeleteById(@Param("id") Integer id);
}
