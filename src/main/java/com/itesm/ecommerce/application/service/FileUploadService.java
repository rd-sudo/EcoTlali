package com.itesm.ecommerce.application.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class FileUploadService {

    @Inject
    Storage storage;

    @ConfigProperty(name = "gcp.bucket.name")
    String bucketName;

    public String upload(byte[] archivoBytes, String nombreArchivo) {
        BlobId blobId = BlobId.of(bucketName, nombreArchivo);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, archivoBytes);
        return blob.getMediaLink();
    }
}

