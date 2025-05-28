package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.repository.UserRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepositoryImpl userRepositoryImpl;
    public void UpdateUuid(String email, String hashedPassword, String newUuid) {
        userRepositoryImpl.UpdateUuid(email, hashedPassword, newUuid);
    }
}
