package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Vendor;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;

public interface VendorRepository {

    public Vendor createVendorWithUser(Vendor vendor, UserEntity userEntity);
    public Vendor getVendorByUserId(int userId);
}
