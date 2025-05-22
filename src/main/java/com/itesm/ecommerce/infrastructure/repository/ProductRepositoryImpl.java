package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.domain.repository.ProductRepository;
import com.itesm.ecommerce.infrastructure.entity.ProductEntity;
import com.itesm.ecommerce.infrastructure.mapper.ProductMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository, PanacheRepositoryBase<ProductEntity,Integer> {

    public List<Product> listAllProducts() {
        List<ProductEntity> productEntities = listAll();
        List<Product> products = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            Product product = ProductMapper.toModel(productEntity);
            products.add(product);
        }
        return products;
    }

}
