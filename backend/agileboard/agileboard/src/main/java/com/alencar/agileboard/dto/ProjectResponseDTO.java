package com.alencar.agileboard.dto;

// Representa os dados que o servidor envia de volta
public record ProjectResponseDTO(
    Long id,
    String title,
    String description
) {
}
