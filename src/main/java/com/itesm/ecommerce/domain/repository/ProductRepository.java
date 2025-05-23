package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Category;
import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.infrastructure.entity.ProductEntity;

import java.util.List;

public interface ProductRepository {

    List<Product> listAllProducts();
}
