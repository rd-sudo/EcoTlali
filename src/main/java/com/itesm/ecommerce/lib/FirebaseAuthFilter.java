package com.itesm.ecommerce.lib;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import javax.annotation.Priority;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class FirebaseAuthFilter implements ContainerRequestFilter {

    // Lista de rutas que no requieren autenticación
    private static final List<String> EXCLUDED_PATHS = List.of("/register", "/public", "/auth/login", "/home");

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();
        System.out.println("[INFO] Processing path: " + path);

        // Si la ruta está exenta, permitir el acceso sin autenticación
        if (isExcludedPath(path)) {
            System.out.println("[INFO] Path exempt from authorization: " + path);
            return; // Continuar sin autenticación
        }

        // Extraer el token del encabezado de autorización
        String token = extractToken(requestContext);
        if (token == null) {
            System.err.println("[ERROR] Authorization header missing or invalid for path: " + path);
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Authorization header must be provided and start with 'Bearer'.")
                            .build()
            );
            return; // Finalizar la petición
        }

        // Validar el token y asociarlo al SecurityContext
        validateTokenAndSetSecurityContext(token, requestContext);
    }

    /**
     * Verifica si una ruta está en la lista de rutas exentas de autenticación.
     *
     * @param path Ruta solicitada.
     * @return `true` si la ruta es pública, `false` si requiere autenticación.
     */
    private boolean isExcludedPath(String path) {
        return EXCLUDED_PATHS.contains(path) || path.startsWith("/public");
    }

    /**
     * Extrae y retorna el token del encabezado de autenticación.
     *
     * @param requestContext Contexto de la solicitud HTTP.
     * @return Token extraído o `null` si el encabezado no es válido.
     */
    private String extractToken(ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring("Bearer".length()).trim();
    }

    /**
     * Valida el token de Firebase y asocia el usuario autenticado al SecurityContext.
     *
     * @param token Token de Firebase.
     * @param requestContext Contexto de la solicitud HTTP.
     */
    private void validateTokenAndSetSecurityContext(String token, ContainerRequestContext requestContext) {
        try {
            // Validar el token con Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String userId = decodedToken.getUid();

            // Crear un Principal con el userId
            Principal userPrincipal = new Principal() {
                @Override
                public String getName() {
                    return userId; // devolver el UID del usuario logueado
                }
            };

            // Asignar el SecurityContext personalizado
            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return userPrincipal;
                }

                @Override
                public boolean isUserInRole(String role) {
                    return false; // Podemos implementar lógica de roles en el futuro
                }

                @Override
                public boolean isSecure() {
                    return requestContext.getUriInfo().getAbsolutePath().toString().startsWith("https");
                }

                @Override
                public String getAuthenticationScheme() {
                    return "Bearer"; // Esquema de autenticación
                }
            });

            System.out.println("[INFO] Valid Firebase token for user ID: " + userId);

        } catch (FirebaseAuthException e) {
            System.err.println("[ERROR] Authentication failed: " + e.getMessage());
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid Firebase token.")
                            .build()
            );
        } catch (Exception e) {
            System.err.println("[ERROR] Unexpected error during token validation: " + e.getMessage());
            requestContext.abortWith(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("An unexpected error occurred.")
                            .build()
            );
        }
    }
}