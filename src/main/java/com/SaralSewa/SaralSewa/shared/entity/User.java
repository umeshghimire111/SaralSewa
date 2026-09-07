package com.SaralSewa.SaralSewa.shared.entity;

import com.SaralSewa.SaralSewa.shared.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="users")
public class User extends BaseEntity {

    @Column(name="first_name" , nullable=false ,length=100)
    private String firstName;

    @Column(name="last_name", length=100)
    private String lastName;

    @Column(name="description", length =255)
    private String description;

    @Column(name="email",nullable=false, unique = true, length=150)
    private String email;

    @Column(name="password",nullable = false, length=255)
    private String password;

    @Column(name="address", length=50)
    private String address;

    @Column(name="phone",nullable=false, length=20)
    private String phone;

    @Column(name="profile_image")
    private String profileImage;

    @JoinColumn(name="role_id", referencedColumnName = "id", nullable=false)
    @ManyToOne
    private Role role;

    @JoinColumn(name="status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;

    @Column(name = "last_logged_in_time")
    private LocalDateTime lastLoggedInTime;

    @Column(name = "wrong_password_attempt_count")
    private Integer wrongPasswordAttemptCount = 0;

    @Column(name = "account_locked_until")
    private LocalDateTime accountLockedUntil;

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