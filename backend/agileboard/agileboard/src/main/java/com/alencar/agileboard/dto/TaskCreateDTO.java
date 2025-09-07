package com.alencar.agileboard.dto;

import com.alencar.agileboard.domain.PriorityLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskCreateDTO(
        @NotBlank
        @Size(min = 3, max = 100, message = "O títutlo da tarefa deve ter entre 3 e 100 caracteres")
        String title,

        @Size(max = 500, message = "A descrição deve conter no máximo 500 caracteres")
        String description,

        @NotNull(message = "O nível de prioridade é obrigatório.")
        PriorityLevel priorityLevel
) {
}
