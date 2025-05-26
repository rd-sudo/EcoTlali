package com.itesm.ecommerce.application.usecase.Auth;

import com.itesm.ecommerce.application.service.AuthService;
import com.itesm.ecommerce.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@ApplicationScoped
public class GetUserByBearerTokenUseCase{
    @Inject
    AuthService authService;

    public User execute(@Context SecurityContext securityContext) {
        // Extraer el userId desde el Principal configurado en el SecurityContext por FirebaseAuthFilter
        String userUuid = securityContext.getUserPrincipal() != null
                ? securityContext.getUserPrincipal().getName()
                : null;

        if (userUuid == null || userUuid.isEmpty()) {
            // Si no hay un usuario autenticado, retornar 401 Unauthorized
            return null;
        }
        return authService.getUserByBearerToken(userUuid);
    }
}
