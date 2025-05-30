package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.mapper.UserMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity, Integer> {

    @Override
    @Transactional
    public User createUser(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        persist(userEntity);
        return UserMapper.toDomain(userEntity);
    }

    @Override
    public User getUserById(int userId) {
        UserEntity userEntity = findById(userId);
        return UserMapper.toDomain(userEntity);
    }

    @Override
    public User getUserByFirebaseId(String uuid) {
        UserEntity userEntity = find("uuid", uuid).firstResult();
        if (userEntity == null) {
            return null;
        }
        User userModel = new User();
        userModel.setId(userEntity.getId());
        userModel.setRole(userEntity.getRole());
        userModel.setEmail(userEntity.getEmail());
        return userModel;
    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity userEntity = find("email", email).firstResult();
        if (userEntity == null) {
            return null;
        }
        User userModel = new User();
        userModel.setId(userEntity.getId());
        userModel.setEmail(userEntity.getEmail());
        return userModel;
    }
}
