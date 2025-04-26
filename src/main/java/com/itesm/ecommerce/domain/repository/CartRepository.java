package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.Product;

public interface CartRepository {
    public void createCart(String user);
    public Cart createCartV2();
    public void changeStatus(int cartId, String status);
    public void emptyCart(int cartId);
    public Cart findByUserId(int id);
    public Cart deleteCart(Integer cartId);

}
