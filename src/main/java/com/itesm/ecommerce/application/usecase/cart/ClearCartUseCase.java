package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.application.service.CartService;
import com.itesm.ecommerce.infrastructure.dto.cart.ClearCartDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClearCartUseCase {

    @Inject
    CartService cartService;

    public void execute(ClearCartDto clearCartDto){
        cartService.clearCart(clearCartDto);
    }
}
