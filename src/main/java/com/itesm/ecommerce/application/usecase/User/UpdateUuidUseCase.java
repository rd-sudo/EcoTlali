package com.itesm.ecommerce.application.usecase.User;

import com.itesm.ecommerce.application.service.UserService;
import com.itesm.ecommerce.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateUuidUseCase {
    @Inject
    UserService userService;
    public void execute(String email,String hashedPassword, String userUuid) {
        userService.UpdateUuid(email, hashedPassword, userUuid);
        // Aquí se puede implementar la lógica para actualizar el UUID del usuario
        // Por ejemplo, podrías llamar a un servicio o repositorio que maneje la actualización
        // del UUID en la base de datos.

        // Retornar el nuevo UUID o el UUID actualizado.
    }
}
