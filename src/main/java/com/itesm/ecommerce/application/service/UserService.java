package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.dto.User.UserRegistrationRequestDto;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public User createUser(String uuid){
        User user = userRepository.insertUser(uuid);
        return user;
    }

    public String getFireBaseidById(int id){
        return userRepository.getFirebaseIdByUserId(id);
    }
}
