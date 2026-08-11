package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.CustomerProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfiles, Integer> {

    Optional<CustomerProfiles> findByUserId(Integer userId);

    Optional<CustomerProfiles> findByUserIdAndIsDeletedFalse(Integer userId);

    boolean existsByUserId(Integer userId);

    List<CustomerProfiles> findByIsActiveTrue();

    List<CustomerProfiles> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE CustomerProfiles c SET c.isDeleted = true, c.deletedAt = CURRENT_TIMESTAMP WHERE c.id = :id")
    void softDeleteById(@Param("id") Integer id);
}