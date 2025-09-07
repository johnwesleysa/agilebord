package com.alencar.agileboard.controller;

import com.alencar.agileboard.dto.SprintCreateDTO;
import com.alencar.agileboard.dto.SprintResponseDTO;
import com.alencar.agileboard.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/sprints")
public class SprintController {

    @Autowired
    public SprintService sprintService;

    // CRIA uma nova sprint
    @PostMapping
    public ResponseEntity<SprintResponseDTO> createSprint (@RequestBody SprintCreateDTO sprintDTO) {
        SprintResponseDTO createdSprint = sprintService.createSprint(sprintDTO);

        // Aplicando boa prática de retornar a url com o novo recurso criado no cabeçalho 'Location'
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdSprint.id()).toUri();

        return ResponseEntity.created(location).body(createdSprint);
    }

    // Edita um sprint pelo id
    @PutMapping("/{id}")
    public ResponseEntity<SprintResponseDTO> updateSprint(@PathVariable Long id, @RequestBody SprintCreateDTO sprintDetails) {
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
