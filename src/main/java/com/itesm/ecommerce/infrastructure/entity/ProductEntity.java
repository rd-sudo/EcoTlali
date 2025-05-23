package com.itesm.ecommerce.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor", nullable = false)
    private VendorEntity vendor;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "electricity_produced")
    private BigDecimal electricityProduced;

    @Column(name = "electricity_consumption")
    private BigDecimal electricityConsumption;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false, columnDefinition = "ENUM('Approved', 'Declined', 'Pending') DEFAULT 'Pending'")
    private ApprovalStatus approvalStatus = ApprovalStatus.Pending;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_modify_date", nullable = false)
    private LocalDateTime lastModifyDate;

    @Column(name = "approved_by", nullable = false)
    private String approvedBy;

    @Column(name = "approval_comments", nullable = false)
    private String approvalComments;

    @Column(name = "reviewed_at", nullable = false)
    private LocalDateTime reviewedAt;
}