import { Component, OnInit } from '@angular/core';
import { SprintCard } from '../../components/sprint-card/sprint-card';
import { Sprint } from '../../models/sprint.model';
import { SprintService } from '../../services/sprint';


@Component({
  selector: 'app-sprints',
  imports: [SprintCard],
  templateUrl: './sprints.html',
  styleUrl: './sprints.scss'
})
export class Sprints implements OnInit {
  sprints: Sprint[] = [];
  isLoading = true;

  // TODO: O ID do projeto virá de forma dinâmica (ex: da rota ou de um serviço de estado)
  // Por enquanto, vamos usar um ID fixo para testar.
  private currentProjectId = 1;

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
}
