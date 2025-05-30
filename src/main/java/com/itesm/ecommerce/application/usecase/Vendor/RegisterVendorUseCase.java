package com.itesm.ecommerce.application.usecase.Vendor;

import com.itesm.ecommerce.application.service.UserService;
import com.itesm.ecommerce.application.service.VendorService;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.model.Vendor;
import com.itesm.ecommerce.infrastructure.dto.Vendor.RegisterVendorDTO;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RegisterVendorUseCase {

    @Inject
    UserService userService;

    @Inject
    VendorService vendorService;

    public Vendor execute(RegisterVendorDTO dto) {
        //Primero verificamos que no se haya creado ya el usuario
        if (userService.emailAlreadyUsed(dto.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        //Despues validamos que los campos no vengan vacios
        if (dto.getCompanyName() == null || dto.getCompanyName().isBlank() ||
                dto.getRfc() == null || dto.getRfc().isBlank() ||
                dto.getIne() == null || dto.getIne().isBlank() ||
                dto.getTaxAddress() == null || dto.getTaxAddress().isBlank()){
            throw new IllegalArgumentException("All vendor fields are required");
        }

        // Creamos el usuario
        User user = dto.toUser();
        User createdUser = userService.createUser(user);
        UserEntity userEntity = UserMapper.toEntity(createdUser);

        // Y finalmente creamos al vendedor
        Vendor vendor = dto.toVendor(createdUser);
        return vendorService.createVendorWithUser(vendor, userEntity);
    }
}
