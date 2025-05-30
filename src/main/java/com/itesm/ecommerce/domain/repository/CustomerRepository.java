package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Customer;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;

public interface CustomerRepository {

    public Customer createCustomerWithUser(Customer customer, UserEntity userEntity);
    public Customer getCustomerByUserId(int userId);
}
