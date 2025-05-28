package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.Customer;
import com.itesm.ecommerce.infrastructure.entity.CustomerEntity;

public class CustomerMapper {
    /**
     * Convierte un CustomerEntity en un Customer (de entidad a modelo).
     *
     * @param entity El CustomerEntity a convertir.
     * @return Un objeto Customer.
     */
    public static Customer toModel(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Customer(
                entity.getCustomerId(),
                UserMapper.toModel(entity.getUser()),
                entity.getPhone()
        );

    }

    /**
     * Convierte un Customer en un CustomerEntity (de modelo a entidad).
     *
     * @param model El Customer a convertir.
     * @return Un objeto CustomerEntity.
     */
    public static CustomerEntity toEntity(Customer model) {
        if (model == null) {
            return null;
        }

        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(model.getCustomer_id());
        entity.setUser(UserMapper.toEntity(model.getUser()));
        entity.setPhone(model.getPhone());
        return entity;
    }
}