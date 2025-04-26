package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.CartRepository;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.dto.cart.DeleteCartDto;
import com.itesm.ecommerce.infrastructure.repository.CartRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteCartUseCase {

    @Inject
    CartRepository CartRepository;
    //Lo ideal es que buscara el carrito asociado con el usario pero de momento asi esta bien.
    public String execute(DeleteCartDto deleteCartDto) {
        Cart cart = CartRepository.deleteCart(deleteCartDto.getIdCart());
        if (cart != null) {
            return "Cart with ID " + deleteCartDto.getIdCart() + " was successfully deleted.";
        } else {
            return "Cart with ID " + deleteCartDto.getIdCart() + " does not exist.";
        }
    }


}

