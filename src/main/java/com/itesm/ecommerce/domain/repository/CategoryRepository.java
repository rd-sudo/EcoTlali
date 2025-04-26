package com.itesm.ecommerce.domain.repository;
import com.itesm.ecommerce.domain.model.Category;

public interface CategoryRepository {
    public Category insertCategory(Category category);
    public Category findCategoryById(Integer id);
}
