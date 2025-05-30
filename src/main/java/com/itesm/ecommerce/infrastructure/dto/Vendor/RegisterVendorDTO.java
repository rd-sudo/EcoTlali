package com.itesm.ecommerce.infrastructure.dto.Vendor;

import com.itesm.ecommerce.domain.model.Role;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.model.Vendor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterVendorDTO {
    // Datos del usuario
    private String email;
    private String uuid;

    // Datos del vendedor
    private String companyName;
    private String rfc;
    private String taxAddress;
    private String ine;

    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setUuid(uuid);
        user.setRole(Role.VENDOR);
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public Vendor toVendor(User user) {
        Vendor vendor = new Vendor();
        vendor.setUser(user);
        vendor.setCompanyName(companyName);
        vendor.setRfc(rfc);
        vendor.setTaxAddress(taxAddress);
        vendor.setIne(ine);
        return vendor;
    }
}