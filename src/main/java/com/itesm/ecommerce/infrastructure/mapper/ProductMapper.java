package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.infrastructure.entity.ProductEntity;

public class ProductMapper {

    public static Product toModel(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                VendorMapper.toModel(entity.getVendor()),
                entity.getPrice(),
                entity.getStock(),
                entity.getElectricityProduced(),
                entity.getElectricityConsumption(),
                entity.getApprovalStatus(),
                entity.getApprovedBy(),
                entity.getApprovalComments(),
                entity.getCreatedAt(),
                entity.getLastModifyDate(),
                entity.getReviewedAt()
        );
    }

    public static ProductEntity toEntity(Product model) {
        ProductEntity entity = new ProductEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setVendor(VendorMapper.toEntity( model.getVendor()));
        entity.setPrice(model.getPrice());
        entity.setStock(model.getStock());
        entity.setElectricityProduced(model.getElectricityProduced());
        entity.setElectricityConsumption(model.getElectricityConsumption());
        entity.setApprovalStatus(model.getApprovalStatus());
        entity.setApprovedBy(model.getApprovedBy());
        entity.setApprovalComments(model.getApprovalComments());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setLastModifyDate(model.getLastModifyDate());
        entity.setReviewedAt(model.getReviewedAt());
        return entity;
    }
}