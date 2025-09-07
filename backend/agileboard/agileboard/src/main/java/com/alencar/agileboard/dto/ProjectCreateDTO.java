package com.alencar.agileboard.dto;

// Representa os dados que o servidor recebe, não recebe o ID pois o id é gerado por trás dos panos
public record ProjectCreateDTO(
        String title,
        String description
) {
}
