package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    public User getUserById(int userId) {
        return userRepository.getUserById(userId);
    }

    public User getUserByFirebaseId(String uuid) {
        return userRepository.getUserByFirebaseId(uuid);
    }

    public boolean existsByFirebaseUid(String uuid) {
        return userRepository.getUserByFirebaseId(uuid) != null;
    }

    public boolean emailAlreadyUsed(String email) {
        return userRepository.getUserByEmail(email) != null;
    }


}