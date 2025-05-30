package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.dto.Authorization.FirebaseAuthorizationResponse;

public interface AuthenticationRepository {
    User getUserByBearerToken(String bearerToken);
}
