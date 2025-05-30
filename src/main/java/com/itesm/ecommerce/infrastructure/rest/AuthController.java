package com.itesm.ecommerce.infrastructure.rest;


import com.google.firebase.auth.FirebaseAuth;
import com.itesm.ecommerce.application.usecase.User.GetUserByFirebaseIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/auth")
@Consumes("application/json")
@Produces("application/json")
public class AuthController {

    @Inject
    GetUserByFirebaseIdUseCase getUserByFirebaseIdUseCase;

    @POST
    @Path("/login")
    public Response loginWithFirebase(@HeaderParam("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "").trim();
            String firebaseUid = FirebaseAuth.getInstance().verifyIdToken(token).getUid();

            return Response.ok(getUserByFirebaseIdUseCase.execute(firebaseUid)).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar la solicitud").build();
        }
    }

}