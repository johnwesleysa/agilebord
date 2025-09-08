package com.alencar.agileboard.controller;

import com.alencar.agileboard.dto.ProjectCreateDTO;
import com.alencar.agileboard.dto.ProjectResponseDTO;
import com.alencar.agileboard.dto.SprintCreateDTO;
import com.alencar.agileboard.dto.SprintResponseDTO;
import com.alencar.agileboard.service.ProjectService;
import com.alencar.agileboard.service.SprintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final SprintService sprintService;

    @Autowired
    public ProjectController(ProjectService projectService, SprintService sprintService){
        this.projectService = projectService;
        this.sprintService = sprintService;
    }

    // Criar projeto (sem alterações)
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectCreateDTO projectDTO) {
        ProjectResponseDTO createdProject = projectService.createProject(projectDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdProject.id()).toUri();
        return ResponseEntity.created(location).body(createdProject);
    }

    // Criar sprint (sem alterações)
    @PostMapping("/{projectId}/sprints")
    public ResponseEntity<SprintResponseDTO> createSprintForProject(@PathVariable Long projectId, @Valid @RequestBody SprintCreateDTO sprintDTO) {
        SprintResponseDTO createdSprint = sprintService.createSprintForProject(projectId, sprintDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/sprints/{id}")
                .buildAndExpand(createdSprint.id()).toUri();
        return ResponseEntity.created(location).body(createdSprint);
    }

    // ATUALIZA um projeto existente (CÓDIGO SIMPLIFICADO)
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectCreateDTO projectDetails) {
        ProjectResponseDTO updatedProject = projectService.updateProject(id, projectDetails);
        return ResponseEntity.ok(updatedProject);
    }



    // LISTA todos os projetos (sem alterações)
    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> listProjects(@RequestParam(required = false) String title) {
        List<ProjectResponseDTO> projects = projectService.getAllProjects(title);
        return ResponseEntity.ok(projects);
    }


    // BUSCA um projeto específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id) {
        ProjectResponseDTO project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    // DELETA um projeto do banco
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}