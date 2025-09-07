package com.alencar.agileboard.dto;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        String priorityLevel
) {
}
