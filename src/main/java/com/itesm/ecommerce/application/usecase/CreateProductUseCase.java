package com.itesm.ecommerce.application.usecase;


import com.itesm.ecommerce.application.service.ProductService;
import com.itesm.ecommerce.domain.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateProductUseCase {

    @Inject
    ProductService productService;

    public Product execute(Product product) {
        product=productService.createProduct(product);
        return product;
    }
}