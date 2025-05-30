package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.Customer;
import com.itesm.ecommerce.domain.repository.CustomerRepository;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomerWithUser(Customer customer, UserEntity userEntity) {
        return customerRepository.createCustomerWithUser(customer);
    }

    public Customer getCustomerByUserId(int userId) {
        return customerRepository.getCustomerByUserId(userId);
    }
}
