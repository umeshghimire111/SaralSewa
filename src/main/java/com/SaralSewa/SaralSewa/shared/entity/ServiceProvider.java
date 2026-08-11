package com.SaralSewa.SaralSewa.shared.entity;


import com.SaralSewa.SaralSewa.shared.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_providers")
@Getter
@Setter
public class ServiceProvider extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "profession", length = 100, nullable = false)
    private String profession;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @JoinColumn(name = "approval_status_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ApprovalStatus approvalStatus;

    @Column(name = "average_rating", precision = 10, scale = 2)
    private BigDecimal averageRating;

    @Column(name = "total_reviews")
    private Integer totalReviews;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "deleted_by")
    private Integer deletedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}