package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.Vendor;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.entity.VendorEntity;

public class VendorMapper {

    public static Vendor toDomain(VendorEntity vendorEntity) {
        Vendor vendor = new Vendor();
        vendor.setCompanyName(vendorEntity.getCompanyName());
        vendor.setRfc(vendorEntity.getRfc());
        vendor.setTaxAddress(vendorEntity.getTaxAddress());
        vendor.setIne(vendorEntity.getIne());
        vendor.setReviewedAt(vendorEntity.getReviewedAt());

        if(vendorEntity.getUser() != null) {
            vendor.setUser(UserMapper.toDomain(vendorEntity.getUser()));
        } else {
            vendor.setUser(null);
        }

        return vendor;
    }

    public static VendorEntity toEntity(Vendor vendor, UserEntity userEntity) {
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setCompanyName(vendor.getCompanyName());
        vendorEntity.setRfc(vendor.getRfc());
        vendorEntity.setTaxAddress(vendor.getTaxAddress());
        vendorEntity.setIne(vendor.getIne());
        vendorEntity.setReviewedAt(vendor.getReviewedAt());
        vendorEntity.setUser(userEntity);

        return vendorEntity;
    }

    public static VendorEntity toEntity(Vendor vendor) {
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setCompanyName(vendor.getCompanyName());
        vendorEntity.setRfc(vendor.getRfc());
        vendorEntity.setTaxAddress(vendor.getTaxAddress());
        vendorEntity.setIne(vendor.getIne());
        vendorEntity.setReviewedAt(vendor.getReviewedAt());
        return vendorEntity;
    }

}