package com.itesm.ecommerce.application.usecase.Auth;

import com.itesm.ecommerce.application.service.AuthenticationService;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.repository.AuthenticationRepositoryImp;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

@ApplicationScoped
public class GetUserByBearerTokenUseCase{
    @Inject
    AuthenticationService authenticationService;

    public User execute(@Context SecurityContext securityContext) {
        // Extraer el userId desde el Principal configurado en el SecurityContext por FirebaseAuthFilter
        String userUuid = securityContext.getUserPrincipal() != null
                ? securityContext.getUserPrincipal().getName()
                : null;

        if (userUuid == null || userUuid.isEmpty()) {
            // Si no hay un usuario autenticado, retornar 401 Unauthorized
            return null;
        }
        return authenticationService.getUserIdByBearerTokenUuid(userUuid);
    }
}
