import { Component, OnInit } from '@angular/core';
import { ProjectStatusCard } from '../../components/project-status-card/project-status-card';
import { ProjectService } from '../../services/project';
import { Project } from '../../models/project.model';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-projects',
  imports: [ProjectStatusCard, RouterLink],
  templateUrl: './projects.html',
  styleUrl: './projects.scss'
})
export class Projects implements OnInit {
  projects: Project[] = [];
  isLoading = true;

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
}
