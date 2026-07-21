package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByUserId(Integer userId);

    List<Feedback> findByUserIdAndIsDeletedFalse(Integer userId);

    List<Feedback> findByFeedbackType(String feedbackType);

    List<Feedback> findByIsActiveTrue();

    List<Feedback> findByIsActiveTrueAndIsDeletedFalse();
}