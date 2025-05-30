package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setUuid(userEntity.getUuid());
        user.setRole(userEntity.getRole());
        user.setCreatedAt(userEntity.getCreatedAt());
        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setEmail(user.getEmail());
        userEntity.setUuid(user.getUuid());
        userEntity.setRole(user.getRole());
        userEntity.setCreatedAt(user.getCreatedAt());
        return userEntity;
    }
}