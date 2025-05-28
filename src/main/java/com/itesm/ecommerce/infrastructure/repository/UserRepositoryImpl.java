package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.mapper.UserMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity,Integer> {
    @Transactional
   public void UpdateUuid(String email, String password, String newUuid){
         UserEntity userEntity = find("email = ?1 and hashedPassword = ?2", email, password).firstResult();
         if (userEntity != null) {
              userEntity.setUuid(newUuid);
         }else{
                throw new RuntimeException("User not found");
         }
   };
}
