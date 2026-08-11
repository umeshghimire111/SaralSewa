package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByBookingId(Integer bookingId);

    Page<Review> findByBookingId(Integer bookingId, Pageable pageable);

    Review findByBookingIdAndIsDeletedFalse(Integer bookingId);

    List<Review> findByCustomerId(Integer customerId);

    Page<Review> findByCustomerId(Integer customerId, Pageable pageable);

    List<Review> findByCustomerIdAndIsDeletedFalse(Integer customerId);

    List<Review> findByProviderId(Integer providerId);

    Page<Review> findByProviderId(Integer providerId, Pageable pageable);

    List<Review> findByProviderIdAndIsDeletedFalse(Integer providerId);

    List<Review> findByRatingGreaterThanEqual(Integer rating);

    List<Review> findByIsActiveTrue();

    List<Review> findByIsActiveTrueAndIsDeletedFalse();

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.provider.id = :providerId AND r.isDeleted = false")
    Double getAverageRatingByProvider(@Param("providerId") Integer providerId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.provider.id = :providerId AND r.isDeleted = false")
    long countReviewsByProvider(@Param("providerId") Integer providerId);

    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.isDeleted = true, r.deletedAt = CURRENT_TIMESTAMP WHERE r.id = :id")
    void softDeleteById(@Param("id") Integer id);
}