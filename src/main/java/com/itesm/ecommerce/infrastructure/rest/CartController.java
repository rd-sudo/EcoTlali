package com.itesm.ecommerce.infrastructure.rest;

import com.itesm.ecommerce.application.usecase.cart.AddProductToCartUseCase;
import com.itesm.ecommerce.application.usecase.cart.DeleteCartUseCase;
import com.itesm.ecommerce.application.usecase.cart.FindCartUseCase;
import com.itesm.ecommerce.application.usecase.cart.RemoveProductFromCartUseCase;
import com.itesm.ecommerce.infrastructure.dto.cart.AddProductDto;
import com.itesm.ecommerce.infrastructure.dto.cart.DeleteCartDto;
import com.itesm.ecommerce.infrastructure.dto.cart.RemoveProductDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/cart")
public class CartController {

    @Inject
    AddProductToCartUseCase addProductToCartUseCase;
    @Inject
    FindCartUseCase findCartUseCase;
    @Inject
    DeleteCartUseCase DeleteCartUseCase;
    @Inject
    RemoveProductFromCartUseCase removeProductFromCartUseCase;


    @POST
    @Path("/add")
    public Response addProductToCart(AddProductDto addProductDto) {
        addProductToCartUseCase.execute(addProductDto);
        Map<String,String> response= new HashMap<>();
        response.put("message", "Product added to cart");
        response.put("productId", String.valueOf(addProductDto.getIdProduct()));
        response.put("quantity", String.valueOf(addProductDto.getQuantity()));
        return Response.ok().entity(response).build();
    }
    //Este deberia ser con el ide de un usuario, de momento esta hardcodeado
    @GET
    public Response getCart() {
        return Response.ok().entity(findCartUseCase.execute("H5AkbDHeaEhAgvfGLqyn6r9d0Ua2")).build();
    }

    @DELETE
    @Path("/delete")
    //Deberia ser igual que con el FireBaseUser pero de moemneto vamos a pasarle le id del carrito
    public Response deleteCart(DeleteCartDto deleteCart) {
        String result = DeleteCartUseCase.execute(deleteCart);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return Response.ok().entity(response).build();
    }


    @POST
    @Path("/remove_product")
    public Response removeProductFromCart(RemoveProductDto removeProductDto) {
        removeProductFromCartUseCase.execute(removeProductDto.getProductId(), removeProductDto.getCartId());
        return Response.ok().build();
    }


    @POST
    @Path("/empty-cart")
    public Response emptyCart() {
        return Response.ok().build();
    }
}
