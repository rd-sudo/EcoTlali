package com.itesm.ecommerce.lib;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import javax.annotation.Priority;
import java.io.IOException;
import java.util.List;

//Autor Richy
@Provider
@Priority(Priorities.AUTHENTICATION)
public class FirebaseAuthFilter implements ContainerRequestFilter {

    // Lista de rutas exentas de autenticación
    private static final List<String> EXCLUDED_PATHS = List.of(
            "/register",
            "/public",
            "/auth/login"
    );

    // Mensajes constantes
    private static final String AUTH_HEADER_MISSING = "Authorization header must be provided and start with 'Bearer'.";
    private static final String INVALID_TOKEN = "Invalid Firebase token.";
    private static final String PROCESSING_PATH = "Processing path: ";
    private static final String PATH_EXEMPT = "Path exempt from authorization: ";
    private static final String INVALID_AUTH_HEADER = "Authorization header is missing or invalid.";
    private static final String VALID_TOKEN_USER = "Valid token for user ID: ";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Obtener la ruta solicitada en la petición
        String path = requestContext.getUriInfo().getPath();
        System.out.println("[INFO] " + PROCESSING_PATH + path);

        // Verificar si la ruta está exenta de autenticación
        if (isExcludedPath(path)) {
            System.out.println("[INFO] " + PATH_EXEMPT + path);
            return; // Continuar con la solicitud sin aplicar autenticación
        }

        // Solo procesar el token en la ruta "/auth/login"
        if (path.equals("/auth/login")) {
            String token = extractToken(requestContext);
            if (token == null) {
                System.err.println("[ERROR] " + INVALID_AUTH_HEADER);
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED)
                                .entity(AUTH_HEADER_MISSING)
                                .build()
                );
                return; // Terminar el proceso si el token es inválido
            }

            // Validar el token y agregar el UID del usuario al contexto
            validateTokenAndSetUserContext(token, requestContext);
        } else {
            // Para otras rutas, abortar con 401
            System.err.println("[ERROR] Unauthorized access to protected resource: " + path);
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Unauthorized access to this resource.")
                            .build()
            );
        }
    }

    /**
     * Verifica si una ruta está en la lista de rutas exentas de autenticación.
     *
     * @param path Ruta solicitada por el cliente.
     * @return `true` si la ruta no requiere autenticación, de lo contrario `false`.
     */
    private boolean isExcludedPath(String path) {
        return EXCLUDED_PATHS.contains(path) || path.startsWith("/public");
    }

    /**
     * Extrae el token del encabezado de autorización.
     *
     * @param requestContext Contexto de la solicitud HTTP.
     * @return El token extraído si es válido, o `null` si el encabezado no es válido.
     */
    private String extractToken(ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString("Authorization");

        // Verificar si el encabezado existe y tiene el formato correcto
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null; // Encabezado inválido o ausente
        }

        return authHeader.substring("Bearer".length()).trim();
    }

    /**
     * Valida el token utilizando Firebase y agrega el UID del usuario al contexto de la solicitud.
     *
     * @param token Token de autenticación extraído del encabezado.
     * @param requestContext Contexto de la solicitud HTTP.
     */
    private void validateTokenAndSetUserContext(String token, ContainerRequestContext requestContext) {
        try {
            // Validar el token con Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);

            // Asignar el UID del usuario autenticado en el contexto de la solicitud
            requestContext.setProperty("userId", decodedToken.getUid());
            System.out.println("[INFO] " + VALID_TOKEN_USER + decodedToken.getUid());
        } catch (FirebaseAuthException e) {
            // Error de autenticación (token inválido o no reconocido por Firebase)
            System.err.println("[ERROR] Authentication failed: " + e.getMessage());
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(INVALID_TOKEN)
                            .build()
            );
        } catch (Exception e) {
            // Manejo de cualquier error inesperado
            System.err.println("[ERROR] Unexpected error while validating the token: " + e.getMessage());
            requestContext.abortWith(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("An unexpected error occurred.")
                            .build()
            );
        }
    }
}