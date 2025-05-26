package com.itesm.ecommerce.application.usecase.Product;

import com.itesm.ecommerce.application.service.ProductService;
import com.itesm.ecommerce.domain.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetProductByIdUseCase {
    @Inject
    ProductService productService;

    public Product execute(int id) {
        return productService.getProductById(id);
    }
}
