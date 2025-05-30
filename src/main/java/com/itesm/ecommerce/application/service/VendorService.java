package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.Vendor;
import com.itesm.ecommerce.domain.repository.VendorRepository;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VendorService {

    @Inject
    VendorRepository vendorRepository;

    @Transactional
    public Vendor createVendorWithUser(Vendor vendor, UserEntity userEntity) {
        return vendorRepository.createVendorWithUser(vendor, userEntity);
    }

    public Vendor getVendorByUserId(int userId) {
        return vendorRepository.getVendorByUserId(userId);
    }
}
