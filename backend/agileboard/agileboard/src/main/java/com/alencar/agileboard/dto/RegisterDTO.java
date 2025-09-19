package com.alencar.agileboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank(message = "O nome completo é obrigatório")
        @Size(min = 3, message = "O nome completo deve ter no mínimo 3 Caracteres")
        String fullName,

        @NotBlank(message = "O nome de usuário é obrigatório.")
        @Size(min = 3, message = "O nome de usuário deve ter no mínimo 3 caracteres.")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
        String password
) {
}
