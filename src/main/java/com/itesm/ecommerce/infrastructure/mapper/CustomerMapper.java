package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.Customer;
import com.itesm.ecommerce.infrastructure.entity.CustomerEntity;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;

public class CustomerMapper {

    public static Customer toDomain(CustomerEntity customerEntity) {
        Customer customer = new Customer();
        customer.setName(customerEntity.getName());
        customer.setPhone(customerEntity.getPhone());

        if (customerEntity.getUser() != null) {
            customer.setUser(UserMapper.toDomain(customerEntity.getUser()));
        } else {
            customer.setUser(null);
        }

        return customer;
    }

    public static CustomerEntity toEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.getName());
        customerEntity.setPhone(customer.getPhone());
        customerEntity.setUser(customer.getUser() != null ? UserMapper.toEntity(customer.getUser()) : null);

        return customerEntity;
    }

}