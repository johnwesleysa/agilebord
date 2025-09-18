import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sprint } from '../models/sprint.model';

// DTO para criação
export interface SprintCreateDTO {
  title: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class SprintService {
  // A URL base agora é aninhada sob os projetos
  private apiUrl = '/api/projects';

  constructor(private http: HttpClient) { }

  // Busca todas as sprints de um projeto específico
  getSprintsForProject(projectId: number): Observable<Sprint[]> {
    // No futuro, sua API de backend pode precisar ser ajustada para retornar as sprints de um projeto.
    // Por enquanto, vamos assumir um endpoint GET /api/projects/{projectId}/sprints
    return this.http.get<Sprint[]>(`${this.apiUrl}/${projectId}/sprints`);
  }

  // Cria uma nova sprint dentro de um projeto
  createSprint(projectId: number, sprintData: SprintCreateDTO): Observable<Sprint> {
    return this.http.post<Sprint>(`${this.apiUrl}/${projectId}/sprints`, sprintData);
  }

  // TODO: Implementar getById, update e delete para sprints individuais
  // Ex: getSprintById(sprintId: number) { return this.http.get<Sprint>(`/api/sprints/${sprintId}`); }
}
