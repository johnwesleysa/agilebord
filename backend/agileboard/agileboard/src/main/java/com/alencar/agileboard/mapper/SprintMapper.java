package com.alencar.agileboard.mapper;

import com.alencar.agileboard.domain.Sprint;

import com.alencar.agileboard.dto.SprintCreateDTO;
import com.alencar.agileboard.dto.SprintResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {

    public Sprint toEntity(SprintCreateDTO dto) {
        Sprint sprint = new Sprint();
        sprint.setTitle(dto.title());
        sprint.setDescription(dto.description());
        return sprint;
    }

    public SprintResponseDTO toResponseDTO(Sprint sprint) {
        return new SprintResponseDTO(sprint.getId(), sprint.getTitle(), sprint.getDescription());
    }
}
