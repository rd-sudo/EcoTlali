package com.itesm.ecommerce.application.usecase.User;

import com.itesm.ecommerce.application.service.CustomerService;
import com.itesm.ecommerce.application.service.UserService;
import com.itesm.ecommerce.application.service.VendorService;
import com.itesm.ecommerce.domain.model.Customer;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.model.Vendor;
import com.itesm.ecommerce.infrastructure.dto.Authorization.LoginResponseDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetUserByFirebaseIdUseCase {

    @Inject
    UserService userService;

    @Inject
    CustomerService customerService;

    @Inject
    VendorService vendorService;

    public LoginResponseDTO execute(String uuid) {
        User user = userService.getUserByFirebaseId(uuid);

        if (user == null) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }

        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());

        switch (user.getRole()) {
            case CUSTOMER -> {
                try {
                    Customer customer = customerService.getCustomerByUserId(user.getId());
                    if (customer == null) throw new Exception("Datos de cliente no encontrados");
                    response.setName(customer.getName());
                    response.setPhone(customer.getPhone());
                } catch (Exception e) {
                    throw new RuntimeException("Error al recuperar datos del cliente: " + e.getMessage());
                }
            }
            case VENDOR -> {
                try {
                    Vendor vendor = vendorService.getVendorByUserId(user.getId());
                    if (vendor == null) throw new Exception("Datos de vendedor no encontrados");
                    response.setCompanyName(vendor.getCompanyName());
                    response.setRfc(vendor.getRfc());
                } catch (Exception e) {
                    throw new RuntimeException("Error al recuperar datos del vendedor: " + e.getMessage());
                }
            }
        }

        return response;
    }
}
