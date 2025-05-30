package com.itesm.ecommerce.infrastructure.entity;

import com.itesm.ecommerce.domain.model.ApprovalStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "vendors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private int vendorId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Column(name = "tax_address", nullable = false, length = 255)
    private String taxAddress;

    @Column(name = "approved_by", nullable = false)
    private String approvedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false, columnDefinition = "ENUM('APPROVED', 'DECLINE', 'PENDING') DEFAULT 'PENDING'")
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    @Column(name = "approval_comments", nullable = false, length = 255)
    private String approvalComments;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reviewed_at", nullable = false)
    private LocalDateTime reviewedAt;

    @Column(name = "ine", nullable = false, length = 20)
    private String ine;
}