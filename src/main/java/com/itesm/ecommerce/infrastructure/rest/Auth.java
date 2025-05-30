package com.itesm.ecommerce.infrastructure.rest;

import com.itesm.ecommerce.application.usecase.Auth.ValidateUserPasswordUseCase;
import com.itesm.ecommerce.application.usecase.User.UpdateUuidUseCase;
import com.itesm.ecommerce.infrastructure.dto.Authorization.FirebaseAuthorizationResponse;
import com.itesm.ecommerce.infrastructure.dto.Authorization.UserLoginRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/auth")
@Consumes("application/json")
@Produces("application/json")
public class Auth {
    @Inject
    ValidateUserPasswordUseCase validateUserPasswordUseCase;

    @Inject
    UpdateUuidUseCase updateUuidUseCase;


    @GET
    @Path("/login")
    public Response testAuth(UserLoginRequest request){
        new FirebaseAuthorizationResponse();
        FirebaseAuthorizationResponse response;
        try{
            response = validateUserPasswordUseCase.execute(request.getEmail(), request.getPassword());
        }catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        //Hacer Update de UUid al usario autenticado
        updateUuidUseCase.execute(request.getEmail(), request.getPassword(), response.getLocalId());

        return Response.ok(response).build();
    }
}
