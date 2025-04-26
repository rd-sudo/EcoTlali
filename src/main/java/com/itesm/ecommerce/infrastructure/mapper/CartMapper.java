package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.infrastructure.entity.CartEntity;

public class CartMapper {

    // Convertir de entidad a dominio
    public static Cart toDomain(CartEntity cartEntity) {
        Cart cart = new Cart();
        cart.setId(cartEntity.getId());
        cart.setStatus(cartEntity.getStatus());
        return cart;
    }

    // Convertir de dominio a entidad
    public static CartEntity toEntity(Cart cart) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(cart.getId());
        cartEntity.setStatus(cart.getStatus());
        return cartEntity;
    }
}
