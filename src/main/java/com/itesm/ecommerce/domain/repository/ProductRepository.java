package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Category;
import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.infrastructure.entity.ProductEntity;

import java.util.List;

public interface ProductRepository {
    public void insertProduct(Product product);
    public List<Product> findAllProducts();
    public void assignCategory(int product_id, int category_id);
    public ProductEntity findProductById(Integer productId);
    public void modifyProduct(Product product, Integer productId);
}
