package com.alencar.agileboard.service;

import com.alencar.agileboard.domain.Project;
import com.alencar.agileboard.dto.ProjectCreateDTO;
import com.alencar.agileboard.dto.ProjectResponseDTO;
import com.alencar.agileboard.exception.ResourceNotFoundException;
import com.alencar.agileboard.mapper.ProjectMapper;
import com.alencar.agileboard.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.List;
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

    // Criar projeto
    @Transactional
    public ProjectResponseDTO createProject(ProjectCreateDTO dto) {
        Project projectEntity = projectMapper.toEntity(dto);
        Project savedProject = projectRepository.save(projectEntity);
        return projectMapper.toResponseDTO(savedProject);
    }

    // Listar todos os projetos
    @Transactional(readOnly = true)
    public Page<ProjectResponseDTO> getAllProjects(String title, Pageable pageable){
        Page<Project> projectPage;
        if(title == null || title.isBlank()){
            projectPage = projectRepository.findAll(pageable);
        } else {
            projectPage = projectRepository.findByTitleContainingIgnoreCase(title, pageable);
        }

        return projectPage.map(projectMapper::toResponseDTO);
    }


    // BUSCA por ID
    @Transactional(readOnly = true)
    public ProjectResponseDTO getProjectById(Long id){
        // Busca o projeto ou lança a exceção se não encontrar.
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto com ID " + id + " não encontrado."));
        // Se encontrou, mapeia para o DTO e retorna.
        return projectMapper.toResponseDTO(project);
    }

    // ATUALIZA um projeto existente
    @Transactional
    public ProjectResponseDTO updateProject(Long id, ProjectCreateDTO dto) {
        // Primeiro, garante que o projeto existe. Se não, lança a exceção.
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto com ID " + id + " não encontrado para atualização."));

        // Se existe, atualiza os campos.
        existingProject.setTitle(dto.title());
        existingProject.setDescription(dto.description());
        Project updatedProject = projectRepository.save(existingProject);

        return projectMapper.toResponseDTO(updatedProject);
    }

    // DELETA UM PROJETO
    @Transactional
    public void deleteProject(Long id) {
        // Verifica se o projeto existe antes de tentar deletar.
        if (!projectRepository.existsById(id)){
            throw new ResourceNotFoundException("Projeto com ID " + id + " não encontrado para exclusão.");
        }
        projectRepository.deleteById(id);
    }
}