package com.SaralSewa.SaralSewa.shared.entity;


import com.SaralSewa.SaralSewa.shared.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "provider_documents")
public class ProviderDocument extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
    private ServiceProvider provider;

    @Column(name = "document_name", length = 100, nullable = false)
    private String documentName;

    @Column(name = "document_type", length = 50, nullable = false)
    private String documentType;

    @Column(name = "document_url", length = 255, nullable = false)
    private String documentUrl;

    @Column(name = "verification_status", length = 50, nullable = false)
    private String verificationStatus;

    @Column(name = "description", length = 255)
    private String description;

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

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
