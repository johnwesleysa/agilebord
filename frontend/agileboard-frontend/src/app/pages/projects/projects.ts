import { Component, OnInit } from '@angular/core';
import { ProjectStatusCard } from '../../components/project-status-card/project-status-card';
import { ProjectService } from '../../services/project';
import { Project } from '../../models/project.model';
import { ProjectForm } from '../../components/project-form/project-form';

@Component({
  selector: 'app-projects',
  imports: [ProjectStatusCard, ProjectForm],
  templateUrl: './projects.html',
  styleUrl: './projects.scss'
})
export class Projects implements OnInit {
  projects: Project[] = [];
  isLoading = true;
  isModalOpen = false;
  editingProjectId: number | null = null;

  constructor(private projectService: ProjectService) {}

  ngOnInit(): void {
    this.loadProjects();
  }

  // Este método agora será usado
  loadProjects(): void {
    this.isLoading = true;
    this.projectService.getProjects().subscribe({
      next: (page) => {
        this.projects = page.content;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Falha ao buscar projetos', err);
        this.isLoading = false;
        // TODO: Mostrar erro na UI
      },
      // TODO fazer o delete
    });
  }
  openCreateModal(): void {
    this.editingProjectId = null;
    this.isModalOpen = true;
  }

  // TODO: Conectar este método a um botão de "editar" no card
  openEditModal(projectId: number): void {
    this.editingProjectId = projectId;
    this.isModalOpen = true;
  }

  closeModal(): void {
    this.isModalOpen = false;
  }

  handleFormSubmitted(): void {
    this.loadProjects();
    this.closeModal();
  }
}
