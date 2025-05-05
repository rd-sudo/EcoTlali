package com.itesm.ecommerce.infrastructure.rest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.itesm.ecommerce.application.usecase.User.CreateUserUseCase;
import com.itesm.ecommerce.infrastructure.dto.User.UserRegistrationRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/public")
@Consumes("application/json")
@Produces("application/json")
public class UserController {
    @Inject
    CreateUserUseCase createUserUseCase;

    @GET
    @Path("/")
    public Response testNoAuth() {
        return Response.ok("Entrar sin Authentication successful").build();
    }

    @POST
    @Path("/register")
    public Response registerUser(UserRegistrationRequestDto request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Email and Password are required")
                    .build();
        }

        try {
            // Crear un usuario en Firebase Authentication
            UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                    .setEmail(request.getEmail())
                    .setPassword(request.getPassword());
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(createRequest);

            // Generar un token personalizado para el usuario
            String uid = userRecord.getUid();
            String customToken = FirebaseAuth.getInstance().createCustomToken(uid);


            //Logica para guardar en la base de datos al usuario

            createUserUseCase.execute(uid);
            // Responder con el UID y el custom token generado
            return Response.status(Response.Status.CREATED)
                    .entity("{ \"uid\": \"" + userRecord.getUid() + "\", \"customToken\": \"" + customToken + "\" }")
                    .build();

        } catch (FirebaseAuthException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error registering user: " + e.getMessage())
                    .build();
        }
    }

}
