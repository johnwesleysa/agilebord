package com.alencar.agileboard.dto;

public record TaskCreateDTO(
        String title,
        String description,
        String priorityLevel
) {
}
