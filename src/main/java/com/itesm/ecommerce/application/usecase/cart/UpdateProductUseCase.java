package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.domain.model.CartHasProduct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateProductUseCase {
    public CartHasProduct execute(CartHasProduct cartHasProduct){
        cartHasProduct= new CartHasProduct();
        return cartHasProduct;
    }
}
