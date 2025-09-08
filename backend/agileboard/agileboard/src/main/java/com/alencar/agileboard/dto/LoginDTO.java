package com.alencar.agileboard.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "O nome de usuário é obrigatóirio")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        String password
) {
}
