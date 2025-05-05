package com.itesm.ecommerce.application.usecase.Product;

import com.itesm.ecommerce.application.service.ProductService;
import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.infrastructure.dto.Product.ModifyProductDto;
import com.itesm.ecommerce.infrastructure.mapper.ProductMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ModifyProductUseCase {

    @Inject
    ProductService productService;

    public void execute(ModifyProductDto modifyProductDto) {
        productService.modifyProduct(modifyProductDto);
    }
}
