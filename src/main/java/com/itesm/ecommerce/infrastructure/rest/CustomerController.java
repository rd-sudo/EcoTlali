package com.itesm.ecommerce.infrastructure.rest;

import com.google.firebase.auth.FirebaseAuth;
import com.itesm.ecommerce.application.usecase.Customer.RegisterCustomerUseCase;
import com.itesm.ecommerce.infrastructure.dto.Customer.RegisterCustomerDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/customers")
@Consumes("application/json")
@Produces("application/json")
public class CustomerController {

    @Inject
    RegisterCustomerUseCase registerCustomerUseCase;

    @POST
    @Path("/register")
    public Response registerCustomer(@HeaderParam("Authorization") String authHeader, RegisterCustomerDTO dto) {
        try {
            //Aqui nosotros extraemos el uid del token y lo seteamos al usuario
            String token = authHeader.replace("Bearer", "").trim();
            String uid = FirebaseAuth.getInstance().verifyIdToken(token).getUid();
            dto.setUuid(uid);

            return Response.ok(registerCustomerUseCase.execute(dto)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno en el servidor").build();
        }
    }
}