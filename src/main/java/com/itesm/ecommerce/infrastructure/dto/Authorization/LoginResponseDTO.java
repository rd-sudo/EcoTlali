package com.itesm.ecommerce.infrastructure.dto.Authorization;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {
    private int id;
    private String email;
    private String role;
    private String name;
    private String phone;
    private String companyName;
    private String rfc;
}
