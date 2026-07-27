package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.BookingStatus;
import com.SaralSewa.SaralSewa.shared.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookingStatusRepository extends JpaRepository<BookingStatus, Integer> {

    Status findByCode(String code);

    boolean existsByCode(String code);

    List<BookingStatus> findByIsActiveTrue();

    List<BookingStatus> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE BookingStatus bs SET bs.isDeleted = true WHERE bs.id = :id")
    void softDeleteById(@Param("id") Integer id);
}