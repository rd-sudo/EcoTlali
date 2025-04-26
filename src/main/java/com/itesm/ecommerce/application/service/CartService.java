package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.domain.repository.CartHasProductRepository;
import com.itesm.ecommerce.domain.repository.CartRepository;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.dto.cart.AddProductDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CartService {

    @Inject
    CartRepository cartRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    CartHasProductRepository cartHasProductRepository;


    public Cart findByUserId(int userId){
        return null;
    }

    public void addProductToCart(AddProductDto addProductDto) {
        Cart cart = cartRepository.createCartV2();
        cartHasProductRepository.addProductToCart(addProductDto.getIdCart(), addProductDto.getIdProduct(), addProductDto.getQuantity());
    }

    public void removeProductFromCart(Integer product_id, Integer cart_id) {
        cartHasProductRepository.removeItemFromCart(product_id, cart_id);
    }

    public void clearCart(Cart cart) {
    }
}
