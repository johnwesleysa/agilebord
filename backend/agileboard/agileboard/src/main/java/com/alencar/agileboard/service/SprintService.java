package com.alencar.agileboard.service;

import com.alencar.agileboard.domain.Sprint;
import com.alencar.agileboard.dto.SprintCreateDTO;
import com.alencar.agileboard.dto.SprintResponseDTO;
import com.alencar.agileboard.mapper.SprintMapper;
import com.alencar.agileboard.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SprintService {

    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper;

    @Autowired
    public SprintService(SprintRepository sprintRepository, SprintMapper sprintMapper) {
        this.sprintRepository = sprintRepository;
        this.sprintMapper = sprintMapper;
    }

    // Criar nova sprint
    @Transactional
    public SprintResponseDTO createSprint(SprintCreateDTO dto) {
        Sprint sprintEntity = sprintMapper.toEntity(dto);

        Sprint savedSprint = sprintRepository.save(sprintEntity);

        return sprintMapper.toResponseDTO(savedSprint);
    }

    // Busca uma sprint por title ou lista todos
    @Transactional(readOnly = true)
    public List<SprintResponseDTO> getAllSprints (String title) {
        List<Sprint> sprints;
        if (title == null || title.isBlank()) {
            sprints = sprintRepository.findAll();
        } else {
            sprints = sprintRepository.findByTitleContainingIgnoreCase(title);
        }

        return sprints.stream()
                .map(sprintMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Busca uma sprint por ID
    @Transactional(readOnly = true)
    public Optional<SprintResponseDTO> getSprintById(Long id) {
        return sprintRepository.findById(id)
                .map(sprintMapper::toResponseDTO);
    }

    // Atualiza uma sprint existente, após uma busca por id
    @Transactional
    public Optional<SprintResponseDTO> updateSprint(Long id, SprintCreateDTO dto){
        return sprintRepository.findById(id)
                .map(existingSprint -> {
                    existingSprint.setTitle(dto.title());
                    existingSprint.setDescription(dto.description());

                    Sprint updatedSprint = sprintRepository.save(existingSprint);

                    return sprintMapper.toResponseDTO(updatedSprint);
                });
    }

    // Deleta uma sprint
    public boolean deleteSprint(Long id) {
        if(sprintRepository.existsById(id)) {
            sprintRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
