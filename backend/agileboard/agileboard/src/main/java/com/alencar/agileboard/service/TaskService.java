package com.alencar.agileboard.service;

import com.alencar.agileboard.domain.Sprint;
import com.alencar.agileboard.domain.Task;
import com.alencar.agileboard.dto.TaskCreateDTO;
import com.alencar.agileboard.dto.TaskResponseDTO;
import com.alencar.agileboard.exception.ResourceNotFoundException;
import com.alencar.agileboard.mapper.TaskMapper;
import com.alencar.agileboard.repository.SprintRepository;
import com.alencar.agileboard.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alencar.agileboard.domain.PriorityLevel;
import com.alencar.agileboard.domain.TaskStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository,SprintRepository sprintRepository ,TaskMapper taskMapper){
        this.taskRepository = taskRepository;
        this.sprintRepository = sprintRepository;
        this.taskMapper = taskMapper;
    }

    // Cria uma task
    @Transactional
    public TaskResponseDTO createTaskForSprint(Long sprintId, TaskCreateDTO dto) {
        // 1. Find the parent Sprint
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint with ID " + sprintId + " not found."));

        // 2. Convert the DTO to the Task entity
        Task taskEntity = taskMapper.toEntity(dto);

        // 3. Associate the task with its Sprint
        taskEntity.setSprint(sprint);

        // 4. --- THIS IS THE FIX ---
        //    Set the default status for any new task
        taskEntity.setStatus(TaskStatus.A_FAZER);

        // 5. Save the new task
        Task savedTask = taskRepository.save(taskEntity);

        // 6. Return the response DTO
        return taskMapper.toResponseDTO(savedTask);
    }

    // Busca uma task por title ou lista todas
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getAllTasks (String title) {
        List<Task> tasks;
        if (title == null || title.isBlank()){
            tasks = taskRepository.findAll();
        } else {
            tasks = taskRepository.findByTitleContainingIgnoreCase(title);
        }

        return tasks.stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Busca uma task pelo id
    @Transactional
    public Optional<TaskResponseDTO> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toResponseDTO);
    }

    // Atualiza uma task pelo id
    @Transactional
    public TaskResponseDTO updateTask(Long id, TaskUpdateDTO dto){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa com ID " + id + " não encontrada."));

        // Atualiza todos os campos a partir do DTO
        existingTask.setTitle(dto.title());
        existingTask.setDescription(dto.description());
        existingTask.setPriorityLevel(dto.priorityLevel());
        existingTask.setStatus(dto.status()); // <-- Lógica de atualização do status

        Task updatedTask = taskRepository.save(existingTask);
        return taskMapper.toResponseDTO(updatedTask);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getTasksBySprintId(Long sprintId) {
        return taskRepository.findBySprintId(sprintId).stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public record TaskUpdateDTO(
            String title,
            String description,
            PriorityLevel priorityLevel,
            TaskStatus status // O novo campo para o status do Kanban
    ) {}

    // Deleta uma task
    public boolean deleteTask(Long id) {
        if(taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return  false;
    }
}
