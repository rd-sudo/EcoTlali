package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.mapper.UserMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity,Integer> {
    @Override
    public User getUserById(int userId) {
        UserEntity user = findById(userId);
        if (user == null) {
            return null;
        }
        User userModel = new User();
        userModel.setId(user.getId());
        userModel.setFirebaseId(user.getFirebaseId());
        return userModel;
    }

    @Override
    public User getUserByFirebaseId(String firebaseId) {
        UserEntity user = find("firebaseId", firebaseId).firstResult();
        if (user == null) {
            return null;
        }
        User userModel = new User();
        userModel.setId(user.getId());
        userModel.setFirebaseId(user.getFirebaseId());
        return userModel;

    }

    @Override
    @Transactional
    public User insertUser(String firebaseId){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirebaseId(firebaseId);
        persist(userEntity);
        return UserMapper.toDomain(userEntity);
    }

    public UserEntity getUserEntityByFirebaseId(String firebaseId) {
        return find("firebaseId", firebaseId).firstResult();
    }

    public String getFirebaseIdByUserId(int userId){
        return findById(userId).getFirebaseId();
    }
}
