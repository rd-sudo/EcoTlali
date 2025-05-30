package com.itesm.ecommerce.infrastructure.entity;

import com.itesm.ecommerce.domain.model.ApprovalStatus;
import com.itesm.ecommerce.domain.model.InstallationOption;
import com.itesm.ecommerce.domain.model.ProductCategory;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "installation_option", nullable = false)
    @Enumerated(EnumType.STRING)
    private InstallationOption installationOption;

    @Column(name = "product_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Column(name = "smart_device", nullable = false)
    private Boolean smartDevice;

    @Column(name = "electricity_produced")
    private Double electricityProduced;

    @Column(name = "electricity_consumption")
    private Double electricityConsumption;

    @Column (name = "saving_cost", nullable = false)
    private Double savingCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false, columnDefinition = "ENUM('APPROVED', 'DECLINED', 'PENDING') DEFAULT 'PENDING'")
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_modify_date", nullable = false)
    private LocalDateTime lastModifyDate;

    @Column(name = "approved_by", nullable = false)
    private String approvedBy;

    @Column(name = "approval_comments")
    private String approvalComments;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;
}