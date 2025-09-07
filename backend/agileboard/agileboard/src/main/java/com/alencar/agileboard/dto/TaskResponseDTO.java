package com.alencar.agileboard.dto;

import com.alencar.agileboard.domain.PriorityLevel;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        PriorityLevel priorityLevel,
        Long sprintId
) {
}
