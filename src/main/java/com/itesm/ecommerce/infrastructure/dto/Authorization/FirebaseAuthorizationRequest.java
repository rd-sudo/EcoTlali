package com.itesm.ecommerce.infrastructure.dto.Authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FirebaseAuthorizationRequest {

    private String email;
    private String password;
    private Boolean returnSecureToken;

}
