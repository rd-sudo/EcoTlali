package com.itesm.ecommerce.infrastructure.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import com.itesm.ecommerce.application.usecase.Product.ListProductsUseCase;

@ApplicationScoped
@Path("/products")
@Consumes("application/json")
@Produces("application/json")

public class ProductController {

    @Inject
    ListProductsUseCase listProductsUseCase;


    @GET
    @Path("/list")
    public Response list() {
        return Response.ok(listProductsUseCase.execute()).build();
    };

}
