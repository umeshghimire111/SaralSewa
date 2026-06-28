package com.SaralSewa.SaralSewa.shared.entity;


import com.SaralSewa.SaralSewa.shared.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "provider_profiles")
public class ProviderProfile extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
    private ServiceProvider provider;

    @Column(name = "bio", length = 1000)
    private String bio;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "hourly_rate", precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "district", length = 50)
    private String district;

    @Column(name = "profile_image", length = 255)
    private String profileImage;

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