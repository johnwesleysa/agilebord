package com.alencar.agileboard.service;

import com.alencar.agileboard.domain.Sprint;
import com.alencar.agileboard.domain.Task;
import com.alencar.agileboard.dto.TaskCreateDTO;
import com.alencar.agileboard.dto.TaskResponseDTO;
import com.alencar.agileboard.mapper.TaskMapper;
import com.alencar.agileboard.repository.SprintRepository;
import com.alencar.agileboard.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public TaskResponseDTO createTaskForSprint(Long sprintId, TaskCreateDTO dto){
        //Bucando a entidade pai (sprint)
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint com ID" + sprintId + " não encontrado"));

        Task taskEntity = taskMapper.toEntity(dto);

        //Associação da task criada com a sprint
        taskEntity.setSprint(sprint);

        Task savedTask = taskRepository.save(taskEntity);
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
    public Optional<TaskResponseDTO> updateTask(Long id, TaskCreateDTO dto){
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(dto.title());
                    existingTask.setDescription(dto.description());
                    existingTask.setPriorityLevel(dto.priorityLevel());

                    Task updatedTask = taskRepository.save(existingTask);

                    return taskMapper.toResponseDTO(updatedTask);

                });
    }

    // Deleta uma task
    public boolean deleteTask(Long id) {
        if(taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return  false;
    }
}
