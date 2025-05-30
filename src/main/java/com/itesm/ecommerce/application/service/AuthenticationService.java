package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.dto.Authorization.FirebaseAuthorizationResponse;
import com.itesm.ecommerce.infrastructure.repository.AuthenticationRepositoryImp;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    AuthenticationRepositoryImp authenticationRepositoryImp;

    public User getUserIdByBearerTokenUuid(String bearerToken) {
        return authenticationRepositoryImp.getUserByBearerToken(bearerToken);
    }
}

