package com.itesm.ecommerce.application.usecase.Auth;

import com.itesm.ecommerce.application.service.AuthenticationService;
import com.itesm.ecommerce.infrastructure.dto.Authorization.FirebaseAuthorizationResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ValidateUserPasswordUseCase {

    @Inject
    AuthenticationService authenticationService;

    public FirebaseAuthorizationResponse execute(String username, String password) throws Exception {
        return authenticationService.validateUserPassword(username, password);
    }
}
