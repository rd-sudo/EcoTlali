package com.itesm.ecommerce.infrastructure.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.itesm.ecommerce.infrastructure.dto.Authorization.FirebaseAuthorizationRequest;
import com.itesm.ecommerce.infrastructure.dto.Authorization.FirebaseAuthorizationResponse;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



@ApplicationScoped
public class AuthenticationRepositoryImp {
    private static final Dotenv dotenv = Dotenv.load(); // Carga las variables del archivo .env
    private static final String FIREBASE_API_URL = dotenv.get("FIREBASE_API_URL"); // URL base de Firebase desde .env

    public FirebaseAuthorizationResponse login(String email, String password) throws Exception {
        // Crear el cuerpo de la solicitud como un JSON
        FirebaseAuthorizationRequest requestBody = new FirebaseAuthorizationRequest();
        requestBody.setEmail(email);
        requestBody.setPassword(password);
        requestBody.setReturnSecureToken(true);

        // Conectarse a la API de Firebase con el Query Param 'key'
        URL url = new URL(FIREBASE_API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Convertir el cuerpo de la solicitud a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

        // Escribir la solicitud (enviar el JSON)
        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonRequestBody.getBytes());
            os.flush();
        }

        // Obtener la respuesta de la API
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { // Verificar que la respuesta sea 200 OK
            // Leer la respuesta como un Stream
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder responseBuilder = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    responseBuilder.append(responseLine);
                }

                // Convertir el JSON de respuesta al DTO FirebaseAuthorizationResponse
                String jsonResponse = responseBuilder.toString();
                return objectMapper.readValue(jsonResponse, FirebaseAuthorizationResponse.class);
            }
        } else {
            // Manejo de errores: si la respuesta no es HTTP 200, arrojar una excepción
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                StringBuilder errorBuilder = new StringBuilder();
                String errorLine;
                while ((errorLine = br.readLine()) != null) {
                    errorBuilder.append(errorLine);
                }
                throw new Exception("Error al autenticar: " + errorBuilder.toString());
            }
        }

    }
}
