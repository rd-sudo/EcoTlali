package com.itesm.ecommerce.infrastructure.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.AuthenticationRepository;
import com.itesm.ecommerce.infrastructure.dto.Authorization.FirebaseAuthorizationRequest;
import com.itesm.ecommerce.infrastructure.dto.Authorization.FirebaseAuthorizationResponse;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.mapper.UserMapper;
import io.github.cdimascio.dotenv.Dotenv;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



@ApplicationScoped
public class AuthenticationRepositoryImp implements AuthenticationRepository, PanacheRepositoryBase<UserEntity, Integer> {
    public User getUserByBearerToken(String bearerToken) {
        // 2. Validar que el uuid no sea nulo
        if (bearerToken == null || bearerToken.isEmpty()) {
            throw new IllegalArgumentException("El UUID no pudo ser extraído del token proporcionado.");
        }

        // 3. Buscar en la base de datos al usuario con el UUID
        UserEntity user = UserEntity.find("uuid", bearerToken).firstResult();
        return UserMapper.toModel(user);
    }
}
