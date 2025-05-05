package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.application.service.CartService;
import com.itesm.ecommerce.infrastructure.dto.cart.PayCartDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PayCartUseCase {

    @Inject
    CartService cartService;

    public void execute(PayCartDto payCartDto){
        cartService.payCart(payCartDto);
    }
}
