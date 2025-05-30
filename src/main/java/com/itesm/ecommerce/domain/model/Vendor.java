package com.itesm.ecommerce.domain.model;

import com.itesm.ecommerce.infrastructure.entity.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    private int vendorId;
    private User user;
    private String rfc;
    private String companyName;
    private String taxAddress;
    private String approvedBy;
    private ApprovalStatus approvalStatus;
    private String approvalComments;
    private LocalDateTime reviewedAt;
}
