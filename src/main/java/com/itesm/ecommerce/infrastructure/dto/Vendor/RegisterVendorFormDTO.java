package com.itesm.ecommerce.infrastructure.dto.Vendor;

import jakarta.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.InputStream;

public class RegisterVendorFormDTO {
    @FormParam("email")
    @PartType("text/plain")
    public String email;

    @FormParam("companyName")
    @PartType("text/plain")
    public String companyName;

    @FormParam("rfc")
    @PartType("text/plain")
    public String rfc;

    @FormParam("taxAddress")
    @PartType("text/plain")
    public String taxAddress;

    @FormParam("nombreArchivo")
    @PartType("text/plain")
    public String nombreArchivo;

    @FormParam("archivo")
    @PartType("application/octet-stream")
    public InputStream archivo;


    public RegisterVendorDTO toDTO(String uid, String ineUrl) {
        RegisterVendorDTO dto = new RegisterVendorDTO();
        dto.setEmail(this.email);
        dto.setUuid(uid);
        dto.setCompanyName(this.companyName);
        dto.setRfc(this.rfc);
        dto.setTaxAddress(this.taxAddress);
        dto.setIne(ineUrl);
        return dto;
    }

}
