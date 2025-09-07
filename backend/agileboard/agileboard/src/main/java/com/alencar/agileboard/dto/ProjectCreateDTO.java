package com.alencar.agileboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Representa os dados que o servidor recebe, não recebe o ID pois o id é gerado por trás dos panos
public record ProjectCreateDTO(

        @NotBlank
        @Size(min = 3, max = 100, message = "O títutlo do projeto deve ter entre 3 e 100 caracteres")
        String title,

        @Size(max = 500, message = "A descrição deve conter no máximo 500 caracteres")
        String description
) {
}
