package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> listAllProducts();
    Product findProductById(int id);
}
