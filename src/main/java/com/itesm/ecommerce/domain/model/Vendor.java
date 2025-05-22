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
    private int vendor_id;
    private User user;
    private String rfc;
    private String company_name;
    private String approved_by;
    private ApprovalStatus approval_status;
    private String approval_comments;
    private LocalDateTime reviewedAt;
}
