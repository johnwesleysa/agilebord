package com.alencar.agileboard.controller;

import com.alencar.agileboard.domain.Project;
import com.alencar.agileboard.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // CRIA um novo Projeto
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        // a anotação @PostMapping converte o JSON recebido em um objeto do tipo Project
        Project savedProject = projectRepository.save(project);

        // retonra o status HTTP 201 Created e o projeto salvo como resposta
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    // ATUALIZA um projeto existente
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {

        return projectRepository.findById(id)
                .map(existingProject -> {
                    existingProject.setTitle(projectDetails.getTitle());
                    existingProject.setDescription(projectDetails.getDescription());

                    Project updateProject = projectRepository.save(existingProject);

                    return ResponseEntity.ok(updateProject);
                })
                .orElse((ResponseEntity.notFound().build()));
    }

    // LISTA todos os projetos ou FILTRA por nome
    @GetMapping
    public List<Project> listProjects(@RequestParam(required = false) String title) {

        if (title == null || title.isBlank()) {
            return projectRepository.findAll();
        }else{
            return projectRepository.findByTitleContainingIgnoreCase(title);
        }
    }

    // BUSCA um projeto específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        // @PathVariable pegar o valor de {id} que foi passado na url e atribui a variavel Long id

        return projectRepository.findById(id)
                .map(project -> ResponseEntity.ok(project))
                .orElse(ResponseEntity.notFound().build());
    }
    // DELETA um projeto do banco
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {

        // Verifico se o id existe, se existe deleta o mesmo
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
