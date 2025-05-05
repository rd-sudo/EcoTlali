package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.CartHasProduct;
import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.domain.repository.CartRepository;
import com.itesm.ecommerce.infrastructure.entity.CartEntity;
import com.itesm.ecommerce.infrastructure.mapper.CartMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;

@ApplicationScoped
public class CartRepositoryImpl implements CartRepository, PanacheRepositoryBase<CartEntity, Integer> {

    @Inject
    UserRepositoryImpl userRepository;


    @Override
    @Transactional
    public void createCart(String userId) {
        CartEntity cart = new CartEntity();
        cart.setUser(userRepository.getUserEntityByFirebaseId(userId));
        cart.setStatus("active");
        persist(cart);
    }

    @Override
    @Transactional
    public Cart createCartV2() {
        CartEntity cart = new CartEntity();
        //cart.setUser(userRepository.getUserEntityByFirebaseId(userId));
        cart.setStatus("active");
        persist(cart);
        Cart cartModel = new Cart();
        cartModel.setId(cart.getId());
        return cartModel;
    }

    @Override
    @Transactional
    public void createCartV3(Integer idUser){
        CartEntity cart = new CartEntity();
        cart.setUser(userRepository.findById(idUser));
        cart.setStatus("active");
        persist(cart);
    };

    @Override
    public void changeStatus(int cartId, String status) {
        CartEntity cart = findById(cartId);
        if (cart != null) {
            cart.setStatus(status);
            persist(cart);
        }
    }

    @Override
    public void emptyCart(int cartId) {
    }

    @Override
    public Cart findByUserId(int id) {
        CartEntity cart = find("user.id", id).firstResult();
        if (cart == null) {
            return null;
        }
        Cart cartModel = new Cart();
        cartModel.setId(cart.getId());
        cartModel.setStatus(cart.getStatus());
        cartModel.setProductsInCart(new ArrayList<>());
        for(int i = 0; i < cart.getCartHasProductsEntity().size(); i++) {
            CartHasProduct cartHasProduct = new CartHasProduct();
            cartHasProduct.setCart(cartModel);
            Product product = new Product();
            product.setName(cart.getCartHasProductsEntity().get(i).getProduct().getName());
            cartHasProduct.setProduct(product);
            cartHasProduct.setQuantity(cart.getCartHasProductsEntity().get(i).getQuantity());
            cartModel.getProductsInCart().add(cartHasProduct);
        }
        return cartModel;
    }

    @Override
    public CartEntity getCartById(Integer cartId) {
        CartEntity cartEntity = findById(cartId);
        Cart cart= CartMapper.toDomain(cartEntity);
        System.out.println(cart);
        return cartEntity;
    }

    @Override
    @Transactional
    public Cart deleteCart(Integer cartId) {
        CartEntity cartEntity = findById(cartId); // Busca la entidad usando su ID
        if (cartEntity != null) {
            delete(cartEntity); // Elimina la entidad encontrada
        }
        return CartMapper.toDomain(cartEntity); // Devolver la entidad eliminada (si existía)
    }

    @Override
    public void payCart(Integer cartId){
        CartEntity cart = findById(cartId);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found with ID: " + cartId);
        }
        // Realiza la consulta JPQL con Panache
        Double total = find("SELECT SUM(p.price * chp.quantity) " +
                "FROM CartHasProductsEntity chp " +
                "JOIN chp.product p " +
                "WHERE chp.cart.id = ?1", cartId)
                .project(Double.class)
                .firstResult();
        // Manejo del caso si el carrito está vacío
        if (total == null) {
            total = 0.0;
        }
        // Imprime o utiliza el total calculado
        System.out.println("El total del carrito con ID " + cartId + " es: " + total);

        // Cambia el estado del carrito
        cart.setStatus("paid");
        persist(cart);


    }



}
