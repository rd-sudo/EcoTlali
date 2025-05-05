package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.infrastructure.entity.CartEntity;

public interface CartRepository {
    public void createCart(String user);
    public Cart createCartV2();
    public void changeStatus(int cartId, String status);
    public void emptyCart(int cartId);
    public Cart findByUserId(int id);
    public Cart deleteCart(Integer cartId);
    public CartEntity getCartById(Integer cartId);
    public void payCart(Integer cartId);

    public void createCartV3(Integer idUser);
}
