package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.infrastructure.dto.Authorization.FirebaseAuthorizationResponse;
import com.itesm.ecommerce.infrastructure.repository.AuthenticationRepositoryImp;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    AuthenticationRepositoryImp authenticationService;

    public FirebaseAuthorizationResponse validateUserPassword(String email, String password) throws Exception {
        return authenticationService.login(email, password);
    }
}

