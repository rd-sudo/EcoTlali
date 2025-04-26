package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.application.service.CartService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RemoveProductFromCartUseCase {
    @Inject
    CartService cartService;

    public void execute(Integer product_id, Integer cart_id){
        cartService.removeProductFromCart(product_id, cart_id);
    }
}
