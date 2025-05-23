package com.itesm.ecommerce.infrastructure.dto.Authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseAuthorizationResponse {
    private String kind;
    private String localId;
    private String email;
    private String displayName;
    private String idToken;
    private boolean registered;
    private String refreshToken;
    private String expiresIn;

}
