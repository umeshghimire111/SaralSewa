package com.SaralSewa.SaralSewa.shared.entity;

import com.SaralSewa.SaralSewa.shared.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking extends BaseEntity {

    @Column(name = "booking_code", length = 50, nullable = false, unique = true)
    private String bookingCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
    private ServiceProvider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id", referencedColumnName = "id", nullable = false)
    private AvailabilitySlot slot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_status_id", referencedColumnName = "id", nullable = false)
    private Status bookingStatus;

    @Column(name = "service_description", length = 500)
    private String serviceDescription;

    @Column(name = "customer_address", length = 255)
    private String customerAddress;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

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