import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Necessário para diretivas como @for
 // Para os links de navegação
import { Sprint } from '../../models/sprint.model';
import { SprintService } from '../../services/sprint';
import { SprintForm } from '../../components/sprint-form/sprint-form';
import { SprintCard } from '../../components/sprint-card/sprint-card'; // Importar o card

@Component({
  selector: 'app-sprints',
  standalone: true,
  // Adicionar todos os módulos e componentes necessários
  imports: [CommonModule, SprintForm, SprintCard],
  templateUrl: './sprints.html',
  styleUrl: './sprints.scss'
})
export class Sprints implements OnInit {
  sprints: Sprint[] = [];
  isLoading = true;
  isModalOpen = false;

  // Por enquanto, vamos usar um ID de projeto fixo para testar.
  currentProjectId = 1;

  constructor(private sprintService: SprintService) {}

  ngOnInit(): void {
    this.loadSprints();
  }

  loadSprints(): void {
    this.isLoading = true;
    this.sprintService.getSprintsForProject(this.currentProjectId).subscribe({
      next: (data) => {
        this.sprints = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Falha ao buscar sprints', err);
        this.isLoading = false;
      }
    });
  }

  // Métodos para controlar o modal
  openCreateModal(): void {
    this.isModalOpen = true;
  }

  closeModal(): void {
    this.isModalOpen = false;
  }

  handleFormSubmitted(): void {
    this.loadSprints();
    this.closeModal();
  }
}
