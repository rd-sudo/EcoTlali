package com.itesm.ecommerce.infrastructure.dto.Customer;

import com.itesm.ecommerce.domain.model.Customer;
import com.itesm.ecommerce.domain.model.Role;
import com.itesm.ecommerce.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterCustomerDTO {
    // Datos del usuario
    private String email;
    private String uuid;

    // Datos del cliente
    private String name;
    private String phone;

    public User toUser() {
        User user = new User();
        user.setEmail(this.email);
        user.setUuid(this.uuid);
        user.setRole(Role.CUSTOMER);
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public Customer toCustomer(User user) {
        Customer customer = new Customer();
        customer.setUser(user);
        customer.setName(this.name);
        customer.setPhone(this.phone);
        return customer;
    }
}