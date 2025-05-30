package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.Vendor;
import com.itesm.ecommerce.domain.repository.VendorRepository;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.entity.VendorEntity;
import com.itesm.ecommerce.infrastructure.mapper.VendorMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VendorRepositoryImpl implements VendorRepository, PanacheRepositoryBase<VendorEntity, Integer> {

    @Inject
    UserRepositoryImpl userRepository;

    @Override
    @Transactional
    public Vendor createVendorWithUser(Vendor vendor, UserEntity userEntity) {
        VendorEntity vendorEntity = VendorMapper.toEntity(vendor);
        persist(vendorEntity);
        return VendorMapper.toModel(vendorEntity);
    }

    @Override
    public Vendor getVendorByUserId(int userId) {
        //VendorEntity vendorEntity = findById(userId);
        VendorEntity vendorEntity = find("user.id", userId).firstResult();
        return VendorMapper.toModel(vendorEntity);
    }
}
