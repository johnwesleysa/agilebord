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
import com.alencar.agileboard.service.TaskService.TaskUpdateDTO;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // CRIAR uma nova task está em SprintController pois uma task nao existe sem uma sprint

    // Edita um sprint pelo id
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO taskDetails) {
        TaskResponseDTO updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
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
