package com.itesm.ecommerce.infrastructure.entity;

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
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Column(name = "approved_by", nullable = true)
    private String approvedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false, columnDefinition = "ENUM('Approved', 'Declined', 'Pending') DEFAULT 'Pending'")
    private ApprovalStatus approvalStatus = ApprovalStatus.Pending;

    @Column(name = "approval_comments", nullable = true, length = 255)
    private String approvalComments;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reviewed_at", nullable = true)
    private LocalDateTime reviewedAt;

    @Column(name = "tax_address", nullable = false)
    private String taxAddress;

    @Column(name = "ine", nullable = false)
    private String ine;
}