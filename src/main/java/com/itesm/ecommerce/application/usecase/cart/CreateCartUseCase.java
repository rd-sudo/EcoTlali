package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.application.service.CartService;
import com.itesm.ecommerce.infrastructure.dto.cart.CreateCartDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateCartUseCase {
    @Inject
    CartService cartService;
    public void execute(CreateCartDto createCartDto){
        cartService.createCartV2(createCartDto);
    }
}
