package com.itesm.ecommerce.domain.model;

import com.itesm.ecommerce.infrastructure.entity.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private int id; // Corresponde a `product_id`
    private String name; // Corresponde a `name`
    private String description; // Corresponde a `description`
    private Vendor vendor;
    private BigDecimal price; // Corresponde a `price` (DECIMAL -> BigDecimal)
    private int stock; // Corresponde a `stock`
    private BigDecimal electricityProduced; // Corresponde a `electricity_produced` (DECIMAL -> BigDecimal)
    private BigDecimal electricityConsumption; // Corresponde a `electricity_consumption` (DECIMAL -> BigDecimal)
    private ApprovalStatus approvalStatus; // Corresponde a `approval_status` (ENUM -> Enum)
    private String approvedBy; // Corresponde a `approved_by`
    private String approvalComments; // Corresponde a `approval_comments`
    private LocalDateTime createdAt; // Corresponde a `created_at`
    private LocalDateTime lastModifyDate; // Corresponde a `last_modify_date`
    private LocalDateTime reviewedAt; // Corresponde a `reviewed_at`

}