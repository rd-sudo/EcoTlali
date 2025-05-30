package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.Customer;
import com.itesm.ecommerce.domain.repository.CustomerRepository;
import com.itesm.ecommerce.infrastructure.entity.CustomerEntity;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.mapper.CustomerMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerRepositoryImpl implements CustomerRepository, PanacheRepositoryBase<CustomerEntity, Integer> {

    @Inject
    UserRepositoryImpl userRepository;

    @Override
    @Transactional
    public Customer createCustomerWithUser(Customer customer, UserEntity userEntity) {
        UserEntity managedUser = userRepository.findById(userEntity.getId());
        CustomerEntity customerEntity = CustomerMapper.toEntity(customer, managedUser);
        persist(customerEntity);
        return CustomerMapper.toDomain(customerEntity);
    }

    @Override
    public Customer getCustomerByUserId(int userId) {
        //CustomerEntity customerEntity = findById(userId);
        CustomerEntity customerEntity = find("user.id", userId).firstResult();
        return CustomerMapper.toDomain(customerEntity);
    }
}