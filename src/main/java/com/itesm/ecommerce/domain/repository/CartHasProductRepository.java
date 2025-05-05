package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Cart;

public interface CartHasProductRepository {

    void addProductToCart(int cartId, int productId, int quantity);
    void removeItemFromCart(Integer product_id, Integer cart_id);
    void clearCart(Integer cart_id);
}
