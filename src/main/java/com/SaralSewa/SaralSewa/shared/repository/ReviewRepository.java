package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByBookingId(Integer bookingId);

    Review findByBookingIdAndIsDeletedFalse(Integer bookingId);

    List<Review> findByCustomerId(Integer customerId);

    List<Review> findByCustomerIdAndIsDeletedFalse(Integer customerId);

    List<Review> findByProviderId(Integer providerId);

    List<Review> findByProviderIdAndIsDeletedFalse(Integer providerId);

    List<Review> findByRatingGreaterThanEqual(Integer rating);

    List<Review> findByIsActiveTrue();

    List<Review> findByIsActiveTrueAndIsDeletedFalse();
}