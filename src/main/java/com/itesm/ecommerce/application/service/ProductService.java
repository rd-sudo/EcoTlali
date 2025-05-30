package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.domain.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.listAllProducts();
    }

    public Product getProductById(int id) {
        return productRepository.findProductById(id);
    }



}