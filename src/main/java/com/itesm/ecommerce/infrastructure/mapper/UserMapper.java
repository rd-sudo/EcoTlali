package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;

public class UserMapper {

    /**
     * Convierte un UserEntity en un User (de entidad a modelo).
     *
     * @param entity El UserEntity a convertir.
     * @return Un objeto User.
     */
    public static User toModel(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getUsername(),
                entity.getPhone(),
                entity.getCreatedAt(),
                entity.getPhone()
        );
    }

    /**
     * Convierte un User en un UserEntity (de modelo a entidad).
     *
     * @param model El User a convertir.
     * @return Un objeto UserEntity.
     */
    public static UserEntity toEntity(User model) {
        if (model == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setEmail(model.getEmail());
        entity.setUsername(model.getUsername());
        entity.setPhone(model.getPhone());
        entity.setCreatedAt(model.getCreatedAt());
        return entity;
    }
}