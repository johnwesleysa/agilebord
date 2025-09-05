package com.alencar.agileboard.controller;

import com.alencar.agileboard.domain.Task;
import com.alencar.agileboard.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // CRIA uma nova task
    @PostMapping
    public ResponseEntity<Task> createTask (@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);

        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // EDITA a task pelo id
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {

        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(taskDetails.getTitle());
                    existingTask.setDescription(taskDetails.getDescription());
                    existingTask.setPriorityLevel(taskDetails.getPriorityLevel());

                    Task updateTask = taskRepository.save(existingTask);

                    return ResponseEntity.ok(updateTask);
                })
                .orElse((ResponseEntity.notFound().build()));
    }

    @GetMapping
    public List<Task> listTask(@RequestParam(required = false) String title) {

        if (title == null || title.isBlank()) {
            return taskRepository.findAll();
        }else{
            return taskRepository.findByTitleContainingIgnoreCase(title);
        }
    }

    // BUSCA uma task específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok(task))
                .orElse(ResponseEntity.notFound().build());
    }
    // DELETA uma task do banco
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {

        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
