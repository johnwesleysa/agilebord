package com.alencar.agileboard.mapper;

import com.alencar.agileboard.domain.Task;
import com.alencar.agileboard.dto.TaskCreateDTO;
import com.alencar.agileboard.dto.TaskResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskCreateDTO dto){
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setPriorityLevel(dto.priorityLevel());
        return task;
    }

    public TaskResponseDTO toResponseDTO(Task task){
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriorityLevel(),
                task.getSprint().getId());
    }
}