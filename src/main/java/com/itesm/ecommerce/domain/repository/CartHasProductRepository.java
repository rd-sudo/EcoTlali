package com.itesm.ecommerce.domain.repository;

public interface CartHasProductRepository {

    public void addProductToCart(int cartId, int productId, int quantity);
    public void removeItemFromCart(Integer product_id, Integer cart_id);
}
