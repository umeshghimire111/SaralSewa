package com.SaralSewa.SaralSewa.shared.repository;


import com.SaralSewa.SaralSewa.shared.entity.OTPCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTPCode, Long> {

    Optional<OTPCode> findByEmailAndOtpCodeAndIsVerifiedFalse(String email, String otpCode);

    @Modifying
    @Transactional
    @Query("DELETE FROM OTPCode o WHERE o.email = :email AND o.isVerified = false")
    void deleteByEmailAndIsVerifiedFalse(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE OTPCode o SET o.isVerified = true WHERE o.email = :email AND o.otpCode = :otpCode")
    void markAsVerified(@Param("email") String email, @Param("otpCode") String otpCode);

    long countByEmailAndCreatedAtAfter(String email, LocalDateTime dateTime);
}