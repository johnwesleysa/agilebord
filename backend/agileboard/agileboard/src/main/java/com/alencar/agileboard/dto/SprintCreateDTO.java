package com.alencar.agileboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SprintCreateDTO(

        @NotBlank
        @Size(min = 3, max = 100, message = "O títutlo da sprint deve ter entre 3 e 100 caracteres")
        String title,

        @Size(max = 500, message = "A descrição deve conter no máximo 500 caracteres")
        String description
) {

}
