package com.itesm.ecommerce.application.usecase.Product;


import com.itesm.ecommerce.application.service.ProductService;
import com.itesm.ecommerce.domain.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateProductUseCase {

    @Inject
    ProductService productService;

    public void execute(Product product) {
        productService.createProduct(product);
    }
}