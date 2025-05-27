package com.itesm.ecommerce.domain.repository;

public interface UserRepository {
    void UpdateUuid(String username, String password, String newUuid);
}
