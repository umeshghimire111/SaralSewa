package com.SaralSewa.SaralSewa.shared.entity;


import com.SaralSewa.SaralSewa.shared.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking_status_history")
@Getter
@Setter
public class BookingStatusHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "old_status_id", referencedColumnName = "id")
    private Status oldStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_status_id", referencedColumnName = "id", nullable = false)
    private Status newStatus;

    @Column(name = "remarks", length = 255)
    private String remarks;

    @Column(name = "changed_by")
    private Integer changedBy;

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