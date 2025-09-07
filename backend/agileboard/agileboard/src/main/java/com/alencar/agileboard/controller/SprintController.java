package com.alencar.agileboard.controller;

import com.alencar.agileboard.dto.SprintCreateDTO;
import com.alencar.agileboard.dto.SprintResponseDTO;
import com.alencar.agileboard.service.SprintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.alencar.agileboard.dto.TaskCreateDTO;
import com.alencar.agileboard.dto.TaskResponseDTO;
import com.alencar.agileboard.service.TaskService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/sprints")
public class SprintController {

    private final SprintService sprintService;
    private final TaskService taskService;

    @Autowired
    public SprintController(SprintService sprintService, TaskService taskService) {
        this.sprintService = sprintService;
        this.taskService = taskService;
    }

    // CRIAR uma nova sprint está em projectController, pois uma sprint só pode existir caso esteja atrelada a um projeto

    // CRIAR uma task associada a uma sprint
    @PostMapping("/{sprintId}/tasks")
    public ResponseEntity<TaskResponseDTO> createTaskForSprint(@PathVariable Long sprintId, @Valid @RequestBody TaskCreateDTO taskDTO){
        TaskResponseDTO createdTask = taskService.createTaskForSprint(sprintId, taskDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/tasks/{id}")
                .buildAndExpand(createdTask.id()).toUri();

        return ResponseEntity.created(location).body(createdTask);
    }


    // Edita um sprint pelo id
    @PutMapping("/{id}")
    public ResponseEntity<SprintResponseDTO> updateSprint(@Valid @PathVariable Long id, @RequestBody SprintCreateDTO sprintDetails) {
        return sprintService.updateSprint(id, sprintDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // LISTA todas as sprints ou busca pelo title
    @GetMapping
    public ResponseEntity<List<SprintResponseDTO>> listSprints(@RequestParam(required = false) String title) {
        List<SprintResponseDTO> sprints = sprintService.getAllSprints(title);
        return ResponseEntity.ok(sprints);
    }

    // BUSCA uma sprint pelo id da mesma
    @GetMapping("/{id}")
    public ResponseEntity<SprintResponseDTO> getSprintById(@PathVariable Long id) {
        return sprintService.getSprintById(id)
                .map(sprintDTO -> ResponseEntity.ok(sprintDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETA uma sprint do banco
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprintById(@PathVariable Long id) {
        if (sprintService.deleteSprint(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
