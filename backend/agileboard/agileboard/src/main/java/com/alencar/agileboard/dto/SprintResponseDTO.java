package com.alencar.agileboard.dto;

public record SprintResponseDTO(
        Long id,
        String title,
        String description,
        Long projectId
) {
}
