package com.itesm.ecommerce.application.usecase;

import com.itesm.ecommerce.application.service.CategoryService;
import com.itesm.ecommerce.domain.model.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FinCategoryUseCase {

    @Inject
    CategoryService categoryService;

    public Category execute(Integer id){
        return categoryService.getCategoryById(id);
    }
}
