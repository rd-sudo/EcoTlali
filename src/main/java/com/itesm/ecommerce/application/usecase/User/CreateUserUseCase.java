package com.itesm.ecommerce.application.usecase.User;

import com.itesm.ecommerce.application.service.ProductService;
import com.itesm.ecommerce.application.service.UserService;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.dto.User.UserRegistrationRequestDto;
import com.itesm.ecommerce.infrastructure.dto.category.AssignCategoryToProductDTO;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateUserUseCase {
    @Inject
    UserService userService;

    public User execute(String uuid){
        User user = userService.createUser(uuid);
        return user;
    }


}


