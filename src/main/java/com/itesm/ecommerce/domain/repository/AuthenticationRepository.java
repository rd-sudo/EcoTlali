package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.infrastructure.dto.Authorization.FirebaseAuthorizationResponse;

public interface AuthenticationRepository {

    FirebaseAuthorizationResponse login(String email, String password);

}
