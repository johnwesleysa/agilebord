package com.alencar.agileboard.controller;

import com.alencar.agileboard.domain.Task;
import com.alencar.agileboard.dto.TaskCreateDTO;
import com.alencar.agileboard.dto.TaskResponseDTO;
import com.alencar.agileboard.repository.TaskRepository;
import com.alencar.agileboard.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // CRIA uma nova task
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask (@Valid @RequestBody TaskCreateDTO taskDTO) {
        TaskResponseDTO createdTask = taskService.createTask(taskDTO);
        
        // Boa prática para retornar a url com o novo recurso criado no cabeçaçho 'Location'
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdTask.id()).toUri();
        
        return ResponseEntity.created(location).body(createdTask);
    }

    // Edita um sprint pelo id
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id,@Valid @RequestBody TaskCreateDTO taskDetails) {
        return taskService.updateTask(id, taskDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // LISTA todos as tasks ou busca pelo title
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listTasks(@RequestParam(required = false) String title){
        List<TaskResponseDTO> tasks = taskService.getAllTasks(title);
        return ResponseEntity.ok(tasks);
    }

    // BUSCA uma task pelo id da mesma
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(taskDTO -> ResponseEntity.ok(taskDTO))
                .orElse(ResponseEntity.notFound().build());
    }
    // DELETA uma task do banco
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
