package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.Vendor;
import com.itesm.ecommerce.infrastructure.entity.VendorEntity;

public class VendorMapper {

    /**
     * Convierte un VendorEntity en un Vendor (de entidad a modelo).
     *
     * @param entity El VendorEntity a convertir.
     * @return Un objeto Vendor.
     */
    public static Vendor toModel(VendorEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Vendor(
                entity.getId(),
                entity.getUser() != null ? UserMapper.toModel(entity.getUser()) : null,
                entity.getRfc(),
                entity.getCompanyName(),
                entity.getApprovedBy(),
                entity.getApprovalStatus(),
                entity.getApprovalComments(),
                entity.getReviewedAt()
        );

    }

    /**
     * Convierte un Vendor en un VendorEntity (de modelo a entidad).
     *
     * @param model El Vendor a convertir.
     * @return Un objeto VendorEntity.
     */
    public static VendorEntity toEntity(Vendor model) {
        if (model == null) {
            return null;
        }

        VendorEntity entity = new VendorEntity();
        entity.setId(model.getVendor_id());
        entity.setUser(model.getUser() != null ? UserMapper.toEntity(model.getUser()) : null);
        entity.setRfc(model.getRfc());
        entity.setCompanyName(model.getCompany_name());
        entity.setApprovedBy(model.getApproved_by());
        entity.setApprovalStatus(model.getApproval_status());
        entity.setApprovalComments(model.getApproval_comments());
        entity.setReviewedAt(model.getReviewedAt());
        return entity;
    }
}