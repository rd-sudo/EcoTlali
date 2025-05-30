package com.itesm.ecommerce.lib;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.FileInputStream;
import java.io.IOException;

@ApplicationScoped
public class GCPInitializer {

    @ConfigProperty(name = "gcp.credentials.path")
    String credentialsPath;

    @Produces
    public Storage produceStorage() {
        try {
            return StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(credentialsPath)))
                    .build()
                    .getService();
        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar Google Cloud Storage", e);
        }
    }
}