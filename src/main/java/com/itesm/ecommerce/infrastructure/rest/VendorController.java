package com.itesm.ecommerce.infrastructure.rest;

import com.google.firebase.auth.FirebaseAuth;
import com.itesm.ecommerce.application.service.FileUploadService;
import com.itesm.ecommerce.application.usecase.Vendor.RegisterVendorUseCase;
import com.itesm.ecommerce.infrastructure.dto.Vendor.RegisterVendorDTO;
import com.itesm.ecommerce.infrastructure.dto.Vendor.RegisterVendorFormDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@ApplicationScoped
@Path("/vendors")
@Consumes("multipart/form-data")
@Produces("application/json")
public class VendorController {

    @Inject
    RegisterVendorUseCase registerVendorUseCase;

    @Inject
    FileUploadService fileUploadService;

    @POST
    @Path("/register")
    public Response registerVendor(@HeaderParam("Authorization") String authHeader, @MultipartForm RegisterVendorFormDTO form) {
        try {
            //Aqui nosotros extraemos el uid del token y lo seteamos al usuario
            String token = authHeader.replace("Bearer", "").trim();
            String uid = FirebaseAuth.getInstance().verifyIdToken(token).getUid();

            // Convertimos imagen a bytes
            byte[] bytes = form.archivo.readAllBytes();

            // Subimos imagen y obtenemos URL
            String url = fileUploadService.upload(bytes, form.nombreArchivo);

            // Creamos DTO para registrar al vendor
            RegisterVendorDTO dto = form.toDTO(uid, url);

            return Response.ok(registerVendorUseCase.execute(dto)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error interno en el servidor").build();
        }
    }

}

