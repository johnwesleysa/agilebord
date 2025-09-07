package com.alencar.agileboard.service;

import com.alencar.agileboard.domain.Project;
import com.alencar.agileboard.dto.ProjectCreateDTO;
import com.alencar.agileboard.dto.ProjectResponseDTO;
import com.alencar.agileboard.mapper.ProjectMapper;
import com.alencar.agileboard.repository.ProjectRepository;

// usar org.springframework.transaction.annotation.Transactional
// nao usar javax.transaction.Transactional
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    //Criar projeto
    //recebe um DTO e retorna um DTO

    @Transactional
    public ProjectResponseDTO createProject(ProjectCreateDTO dto) {
        //Aqui converte o DTO para a entidade project
        Project projectEntity = projectMapper.toEntity(dto);

        Project savedProject = projectRepository.save(projectEntity);

        return projectMapper.toResponseDTO(savedProject);
    }

    // BUSCA um projeto específico por ID
    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getAllProjects(String title) {
        List<Project> projects;
        if (title == null || title.isBlank()){
            projects = projectRepository.findAll();
        } else {
            projects = projectRepository.findByTitleContainingIgnoreCase(title);
        }

        return projects.stream()
                .map(projectMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //BUSCA por ID
    @Transactional(readOnly = true)
    public Optional<ProjectResponseDTO> getProjectById(Long id){
        return projectRepository.findById(id)
                .map(projectMapper::toResponseDTO);
    }

    //ATUALIZA um projeto existente, após a busca por id
    @Transactional
    public Optional<ProjectResponseDTO> updateProject(Long id, ProjectCreateDTO dto) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    existingProject.setTitle(dto.title());
                    existingProject.setDescription(dto.description());

                    Project updatedProject = projectRepository.save(existingProject);

                    return projectMapper.toResponseDTO(updatedProject);
                });
    }

    // DELETA UM PROJETO
    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)){
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
