package com.SaralSewa.SaralSewa.shared.entity;

import com.SaralSewa.SaralSewa.shared.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name="roles")
@Entity
@Getter
@Setter

public class Role extends BaseEntity {

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Column(name="description", length=255)
    private String description;

    @Column(name="code", nullable=false, length=50)
    private String code;

    @Column(name="is_active", nullable=false)
    private Boolean isActive;

    @Column(name="is_deleted",nullable=false)
    private Boolean isDeleted;

    @Column(name="created_by")
    private Integer createdBy;

    @Column(name="updated_by")
    private Integer updatedBy;

    @Column(name="deleted_by")
    private Integer deletedBy;

    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;


}
