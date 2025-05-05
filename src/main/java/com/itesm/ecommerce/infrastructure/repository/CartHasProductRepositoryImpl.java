package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.CartHasProduct;
import com.itesm.ecommerce.domain.repository.CartHasProductRepository;
import com.itesm.ecommerce.domain.repository.CartRepository;
import com.itesm.ecommerce.domain.repository.ProductRepository;
import com.itesm.ecommerce.infrastructure.entity.CartEntity;
import com.itesm.ecommerce.infrastructure.entity.CartHasProductsEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CartHasProductRepositoryImpl implements CartHasProductRepository, PanacheRepository<CartHasProductsEntity> {
    @Inject
    CartRepository cartRepository;

    @Inject
    ProductRepository productRepository;
    @Override
    public void addProductToCart(int cartId, int productId, int quantity) {
        System.out.println("Adding product with ID " + productId + " to cart with ID " + cartId + " with quantity " + quantity);
        CartHasProductsEntity cartHasProduct = new CartHasProductsEntity();
        cartHasProduct.setCart(cartRepository.getCartById(cartId));
        cartHasProduct.setProduct(productRepository.findProductById(productId));
        cartHasProduct.setQuantity(quantity);
        persist(cartHasProduct);
        System.out.println("Product added to cart successfully.");

    }

    @Override
    @Transactional
    public void removeItemFromCart(Integer product_id, Integer cart_id) {
        delete("cart.id = ?1 AND product.id = ?2", cart_id, product_id);
    }

    @Override
    @Transactional
    public void clearCart(Integer cart_id){
        CartEntity cart = cartRepository.getCartById(cart_id);
        delete ("cart.id = ?1", cart.getId());
    }
}
