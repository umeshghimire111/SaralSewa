package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.Feedback;
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
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByUserId(Integer userId);

    Page<Feedback> findByUserId(Integer userId, Pageable pageable);

    List<Feedback> findByUserIdAndIsDeletedFalse(Integer userId);

    List<Feedback> findByFeedbackType(String feedbackType);

    Page<Feedback> findByFeedbackType(String feedbackType, Pageable pageable);

    List<Feedback> findByIsActiveTrue();

    List<Feedback> findByIsActiveTrueAndIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Feedback f SET f.isDeleted = true, f.deletedAt = CURRENT_TIMESTAMP WHERE f.id = :id")
    void softDeleteById(@Param("id") Integer id);
}