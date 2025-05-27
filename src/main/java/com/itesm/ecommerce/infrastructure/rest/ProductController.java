package com.itesm.ecommerce.infrastructure.rest;

import com.itesm.ecommerce.application.usecase.Auth.GetUserByBearerTokenUseCase;
import com.itesm.ecommerce.application.usecase.Product.GetProductByIdUseCase;
import com.itesm.ecommerce.application.usecase.Quote.AddProductoToUserQuoteUseCase;
import com.itesm.ecommerce.application.usecase.Quote.CheckIfProductInUserQuoteUseCase;
import com.itesm.ecommerce.application.usecase.Quote.CreateQuoteForUserUseCase;
import com.itesm.ecommerce.application.usecase.Quote.UpdateProductInQuote;
import com.itesm.ecommerce.domain.model.QuoteItem;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.dto.Quote.AddProductToQuoteDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import com.itesm.ecommerce.application.usecase.Product.ListProductsUseCase;
import jakarta.ws.rs.core.SecurityContext;

@ApplicationScoped
@Path("/products")
@Consumes("application/json")
@Produces("application/json")

public class ProductController {

    @Inject
    ListProductsUseCase listProductsUseCase;

    @Inject
    CreateQuoteForUserUseCase CreateQuoteForUserUseCase;

    @Inject
    CheckIfProductInUserQuoteUseCase checkIfProductInUserQuoteUseCase;

    @Inject
    AddProductoToUserQuoteUseCase addProductoToUserQuoteUseCase;

    @Inject
    UpdateProductInQuote updateProductInQuote;

    @Inject
    GetUserByBearerTokenUseCase getUserByBearerTokenUseCase;

    @Inject
    GetProductByIdUseCase getProductByIdUseCase;

    @GET
    @Path("/list")
    public Response list() {
        listProductsUseCase.execute();
        return Response.ok(listProductsUseCase.execute()).build();
    }

    @POST
    @Path("/addProductToCart")
    public Response addToCart(@Context SecurityContext securityContext, AddProductToQuoteDTO addproductToQuoteDTO) {
        try {
            User actualUser = getUserByBearerTokenUseCase.execute(securityContext);

            // Buscar un producto existente en el carrito del usuario por "quote_id" y "product_id"
            QuoteItem existingItem = checkIfProductInUserQuoteUseCase.execute(
                    actualUser.getId(),
                    addproductToQuoteDTO.getQuote_id(),
                    addproductToQuoteDTO.getProduct_id()

            );
            System.out.println("User id: " +actualUser.getId());

            if (existingItem != null) {// Si el producto ya está en el carrito, actualizar la cantidad
                existingItem.setQuantity(existingItem.getQuantity() + addproductToQuoteDTO.getQuantity());
                updateProductInQuote.execute(existingItem);

            } else {
                // Si el producto no está en el carrito, agregarlo como nuevo
                addProductoToUserQuoteUseCase.execute(
                        actualUser.getId(),
                        addproductToQuoteDTO.getQuote_id(),
                        addproductToQuoteDTO.getProduct_id(),
                        addproductToQuoteDTO.getQuantity()
                );
            }
            // Mensaje final
            return Response.ok("Product added to cart successfully!").build();

        } catch (Exception e) {
            // Manejo de errores genérico
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred: " + e.getMessage()).build();
        }
    }



    //Implentacion Faltante
    @GET
    @Path("/productDetails/{productId}")
    public Response getProductDetails(@PathParam("productId") int productId) {
        getProductByIdUseCase.execute(productId);
        return Response.ok("Product details send succesfully").build();
    }
}
