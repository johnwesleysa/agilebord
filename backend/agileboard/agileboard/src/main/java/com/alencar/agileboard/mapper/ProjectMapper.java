package com.alencar.agileboard.mapper;

import com.alencar.agileboard.domain.Project;
import com.alencar.agileboard.dto.ProjectCreateDTO;
import com.alencar.agileboard.dto.ProjectResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectCreateDTO dto) {
        Project project = new Project();
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        return project;
    }

    public ProjectResponseDTO toResponseDTO(Project project) {
        return new ProjectResponseDTO(project.getId(), project.getTitle(), project.getDescription());
    }
}
