package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByCode(String code);

    Role findByName(String name);

    boolean existsByCode(String code);

    List<Role> findByIsActiveTrue();

    List<Role> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Role r SET r.isDeleted = true, r.deletedAt = CURRENT_TIMESTAMP WHERE r.id = :id")
    void softDeleteById(@Param("id") Integer id);
}
