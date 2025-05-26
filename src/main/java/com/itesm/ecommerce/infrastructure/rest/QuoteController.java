package com.itesm.ecommerce.infrastructure.rest;

import com.itesm.ecommerce.application.usecase.Quote.CreateQuoteForUserUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@ApplicationScoped
@Path("/quote")
@Consumes("application/json")
@Produces("application/json")
public class QuoteController {
    @Inject
    CreateQuoteForUserUseCase CreateQuoteForUserUseCase;

    @POST
    @Path("/createQuote")
    public Response createQuote(@Context SecurityContext securityContext) {
        try {
            // Extraer el userId desde el Principal configurado en el SecurityContext por FirebaseAuthFilter
            String userUuid = securityContext.getUserPrincipal() != null
                    ? securityContext.getUserPrincipal().getName()
                    : null;

            if (userUuid == null || userUuid.isEmpty()) {
                // Si no hay un usuario autenticado, retornar 401 Unauthorized
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("User ID not found in the authentication context").build();
            }

            // Log para verificar el `userId` recibido
            System.out.println("[INFO] Usuario autenticado: " + userUuid);

            // Crear la cotización para el usuario
            CreateQuoteForUserUseCase.execute(userUuid);

            // Mensaje final
            return Response.ok("Quote created successfully!").build();

        } catch (Exception e) {
            // Manejo de errores genérico
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred: " + e.getMessage()).build();
        }
    }

}
