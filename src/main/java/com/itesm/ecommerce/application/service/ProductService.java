package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.Category;
import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.domain.repository.CategoryRepository;
import com.itesm.ecommerce.domain.repository.ProductRepository;
import com.itesm.ecommerce.infrastructure.dto.Product.ModifyProductDto;
import com.itesm.ecommerce.infrastructure.entity.CategoryEntity;
import com.itesm.ecommerce.infrastructure.entity.ProductEntity;
import com.itesm.ecommerce.infrastructure.mapper.CategoryMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;
    @Inject
    CategoryRepository categoryRepository;

    public void createProduct(Product product) {
        product.setUuid(UUID.randomUUID().toString());
        productRepository.insertProduct(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public void assignCategory(int category_id, int product_id) {
        productRepository.assignCategory(product_id,category_id);

    }

    public void modifyProduct(ModifyProductDto modifyProductDto) {
        //Busca el producto por id
        Product newproduct = new Product();
        newproduct.setName(modifyProductDto.getName());
        newproduct.setDescription(modifyProductDto.getDescription());
        newproduct.setPrice(modifyProductDto.getPrice());
        newproduct.setStock(modifyProductDto.getStock());
        //newproduct.setUuid(modifyProductDto.getUuid());

        Category newCategory= categoryRepository.findCategoryById(modifyProductDto.getCategory_id());
        newproduct.setCategory(newCategory);
        productRepository.modifyProduct(newproduct, modifyProductDto.getProduct_id());
    }

}