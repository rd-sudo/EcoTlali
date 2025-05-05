package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.repository.CartHasProductRepository;
import com.itesm.ecommerce.domain.repository.CartRepository;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.dto.cart.AddProductDto;
import com.itesm.ecommerce.infrastructure.dto.cart.ClearCartDto;
import com.itesm.ecommerce.infrastructure.dto.cart.CreateCartDto;
import com.itesm.ecommerce.infrastructure.dto.cart.PayCartDto;
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
        cartHasProductRepository.addProductToCart(addProductDto.getIdCart(), addProductDto.getIdProduct(), addProductDto.getQuantity());
    }

    public void removeProductFromCart(Integer product_id, Integer cart_id) {
        cartHasProductRepository.removeItemFromCart(product_id, cart_id);
    }

    public void clearCart(ClearCartDto clearCartDto) {
        cartHasProductRepository.clearCart(clearCartDto.getIdCart());
    }

    public void payCart(PayCartDto payCartDto) {
        cartRepository.payCart(payCartDto.getIdCart());
    }


    public void createCartV2(CreateCartDto createCartDto) {
        cartRepository.createCartV3(createCartDto.getIdUser());
    }

    public String deleteCart(Integer idCart) {
        Cart cart = cartRepository.deleteCart(idCart);
        if (cart != null) {
            return "Cart with ID " + idCart + " was successfully deleted.";
        } else {
            return "Cart with ID " + idCart + " does not exist.";
        }
    }
}
