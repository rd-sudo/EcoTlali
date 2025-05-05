package com.itesm.ecommerce.application.usecase.User;

import com.itesm.ecommerce.application.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetFirebaseIdByIdUserCase {
    @Inject
    UserService userService;
    public String execute(int id){
        return userService.getFireBaseidById(id);
    }
}
