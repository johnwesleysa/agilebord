package com.alencar.agileboard.dto;

import com.alencar.agileboard.domain.PriorityLevel;
import com.alencar.agileboard.domain.TaskStatus;

// Usando record para um DTO conciso
public record TaskUpdateDTO(
        String title,
        String description,
        PriorityLevel priorityLevel,
        TaskStatus status // O novo campo para o status do Kanban
) {}