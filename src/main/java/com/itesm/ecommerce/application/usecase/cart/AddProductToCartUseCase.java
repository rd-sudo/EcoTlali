package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.application.service.CartService;
import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.CartHasProductRepository;
import com.itesm.ecommerce.domain.repository.CartRepository;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.dto.cart.AddProductDto;
import com.itesm.ecommerce.infrastructure.repository.CartRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AddProductToCartUseCase {

    @Inject
    CartService cartService;


    /*@Transactional
    public void execute(AddProductDto addProductDto, String firebaseId){
        User user = userRepository.getUserByFirebaseId(firebaseId);
        Cart cart= cartRepository.findByUserId(user.getId());
        if(cart==null){
            cartRepository.createCart(user.getFirebaseId());
            cart=cartRepository.findByUserId(user.getId());
        }
        cartHasProductRepository.addProductToCart(cart.getId(),addProductDto.getIdProduct(),addProductDto.getQuantity());

    }
    */

    @Transactional
    public void execute(AddProductDto addProductDto) {
        cartService.addProductToCart(addProductDto);
    }


}