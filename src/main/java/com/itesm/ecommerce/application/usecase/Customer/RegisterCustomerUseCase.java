package com.itesm.ecommerce.application.usecase.Customer;

import com.itesm.ecommerce.application.service.CustomerService;
import com.itesm.ecommerce.application.service.UserService;
import com.itesm.ecommerce.domain.model.Customer;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.dto.Customer.RegisterCustomerDTO;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RegisterCustomerUseCase {

    @Inject
    UserService userService;

    @Inject
    CustomerService customerService;

    public Customer execute(RegisterCustomerDTO dto) {
        //Primero verificamos que su email no este registrado ya
        if (userService.emailAlreadyUsed(dto.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Despues verificamos que los campos no vengan vacios
        if (dto.getName() == null || dto.getPhone() == null || dto.getName().isBlank() || dto.getPhone().isBlank()) {
            throw new IllegalArgumentException("Name and phone are required");
        }

        // Primero creamos al usuario
        User user = dto.toUser();
        User createdUser = userService.createUser(user);
        UserEntity userEntity = UserMapper.toEntity(createdUser);

        // Despues como cliente
        Customer customer = dto.toCustomer(createdUser);
        return customerService.createCustomerWithUser(customer, userEntity);
    }

}
