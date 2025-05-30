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
                entity.getVendorId(),
                UserMapper.toDomain(entity.getUser()),
                entity.getRfc(),
                entity.getCompanyName(),
                entity.getTaxAddress(),
                entity.getApprovedBy(),
                entity.getApprovalStatus(),
                entity.getApprovalComments(),
                entity.getIne(),
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
        entity.setVendorId(model.getVendorId());
        entity.setUser(model.getUser() != null ? UserMapper.toEntity(model.getUser()) : null);
        entity.setRfc(model.getRfc());
        entity.setCompanyName(model.getCompanyName());
        entity.setTaxAddress(model.getTaxAddress());
        entity.setApprovedBy(model.getApprovedBy());
        entity.setApprovalStatus(model.getApprovalStatus());
        entity.setApprovalComments(model.getApprovalComments());
        entity.setReviewedAt(model.getReviewedAt());
        entity.setIne(model.getIne());
        return entity;
    }
}