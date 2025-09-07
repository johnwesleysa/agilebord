package com.alencar.agileboard.controller;

import com.alencar.agileboard.dto.ProjectCreateDTO;
import com.alencar.agileboard.dto.ProjectResponseDTO;
import com.alencar.agileboard.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // CRIA um novo Projeto
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectCreateDTO projectDTO) {
        ProjectResponseDTO createdProject = projectService.createProject(projectDTO);

        //aplicando boa pratica de retornar a url com o novo recurso criado no cabeçalho 'Location'
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdProject.id()).toUri();

        // retonra o status HTTP 201 Created e o projeto salvo como resposta
        return ResponseEntity.created(location).body(createdProject);
    }

    // ATUALIZA um projeto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id, @RequestBody ProjectCreateDTO projectDetails) {
        return projectService.updateProject(id, projectDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // LISTA todos os projetos ou FILTRA por nome
    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> listProjects(@RequestParam(required = false) String title) {
        List<ProjectResponseDTO> projects = projectService.getAllProjects(title);
        return ResponseEntity.ok(projects);
    }

    // BUSCA um projeto específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(projectDTO -> ResponseEntity.ok(projectDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETA um projeto do banco
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {
        if (projectService.deleteProject(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}

